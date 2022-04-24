package com.db.nace;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.db.nace.config.NaceJobConfig;

@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {NaceJobConfig.class})
public class JobTest {

	  private JobExplorer jobExplorer;

	    @Autowired
	    @Qualifier("Nace-Load")
	    private Job JobA;


	    @Autowired
	    private JobLauncherTestUtils jobLauncherTestUtils;


	    @Test
	    public void testJob() throws Exception {
	    	String filePath = "C:\\temp\\nace.csv";
	    	JobParameters jobParameters = new JobParametersBuilder().addString("fullFilePath", filePath)
					.toJobParameters();
		       System.out.print(" File path in controler "+jobParameters.getString("fullFilePath"));
		        JobExecution jobExecution = null;
	       // JobParameters jobParameters = getJobParameters();
	        assertEquals(BatchStatus.COMPLETED, jobLauncherTestUtils.getJobLauncher().run(JobA, jobParameters));
	    }
	    
	    private JobParameters getJobParameters() {
	        JobParametersBuilder jobParameters = new JobParametersBuilder();
	        jobParameters.addString("param", "123");
	        return jobParameters.toJobParameters();
	    }
	    
}