package com.db.nace;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.batch.test.StepScopeTestUtils;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.db.nace.config.NaceJobConfig;
import com.db.nace.model.Nace;

import antlr.collections.List;

@SpringBootTest
public class NaceBatchConfigTest {
	
	@Autowired
	NaceJobConfig naceJobConfig;
	
	Job job;
	
	 @Autowired
	  JobLauncher jobLauncher;
	
	JobBuilderFactory jobBuilderFactory;
    StepBuilderFactory stepBuilderFactory;
    ItemReader<Nace> itemReader;
    ItemProcessor<Nace, Nace> itemProcessor;
    ItemWriter<Nace> itemWrite;
	
	@SuppressWarnings("unchecked")
	@Before(value = "")
	public void setup() {
		naceJobConfig = new NaceJobConfig();
		jobBuilderFactory = mock(JobBuilderFactory.class);
		stepBuilderFactory = mock(StepBuilderFactory.class);
		itemReader = mock(ItemReader.class);
		itemProcessor = mock(ItemProcessor.class);
		itemWrite = mock(ItemWriter.class);
		job = naceJobConfig.job(jobBuilderFactory, stepBuilderFactory, itemReader, itemProcessor, itemWrite);
	}
	
	@Test
	public void testJob() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		String filePath="C:\\temp\\naceTest.csv";
		/*
		 * JobParameters jobParameters = new
		 * JobParametersBuilder().addString("fullFilePath", filePath)
		 * .toJobParameters();
		 * System.out.print(" File path in controler "+jobParameters.getString(
		 * "fullFilePath")); JobExecution jobExecution = jobLauncher.run(job,
		 * jobParameters);
		 */
	       
	       FlatFileItemReader<Nace> flatFileReader =  naceJobConfig.itemReader(filePath);
	       
	       try {
			flatFileReader.read();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       
	  //     assertEquals(jobExecution, "Completed");
	       
	       // given
	        ExecutionContext ctx = new ExecutionContext();
	        ctx.put("fileName", "data/input.txt");
	       
		
	}

}
