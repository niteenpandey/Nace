package com.db.nace.service;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.nace.model.Nace;
import com.db.nace.repository.NaceRepository;

@Service
public class NaceServiceImpl implements NaceService {
	

	  @Autowired 
	  NaceRepository naceRepositry;
	  
	  @Autowired
	  JobLauncher jobLauncher;

	  @Autowired
	  Job job;

	 

	@Override
	public Nace findByOrderId(String orderId) {
		return  naceRepositry.findByOrderid(orderId);
	}


	@Override
	public BatchStatus putNaceDetails(String filePath) {
		JobParameters jobParameters = new JobParametersBuilder().addString("fullFilePath", filePath)
				.toJobParameters();
	       System.out.print(" File path in controler "+jobParameters.getString("fullFilePath"));
	        JobExecution jobExecution = null;
			try {
				jobExecution = jobLauncher.run(job, jobParameters);
			} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
					| JobParametersInvalidException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        System.out.println("JobExecution: " + jobExecution.getStatus());

	        System.out.println("Batch is Running...");
	       

		return jobExecution != null ? jobExecution.getStatus() : null;
	}

	
	
}
