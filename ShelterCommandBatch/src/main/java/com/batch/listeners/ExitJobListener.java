package com.batch.listeners;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

public class ExitJobListener implements JobExecutionListener{

    @Override
    public void beforeJob(JobExecution jobExecution) {
    	/*JobParameters jobParameters =
	  			  new JobParametersBuilder()
	  			  .addLong("time",System.currentTimeMillis()).toJobParameters();*/
    	//jobExecution.getJobParameters().
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        // change exit message
        ExitStatus es = jobExecution.getExitStatus();
        jobExecution.setExitStatus(new ExitStatus(es.getExitCode(), "foo"));
    }
    
}