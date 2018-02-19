package com.batch.core;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

//@Component
public class CustomJobScheduler {

  @Autowired
  private JobLauncher jobLauncher;

  @Autowired
  private Job job;

  public void run() {

    try {

    	 JobParameters jobParameters=null;
	        
	        
         // get next long value 
         long value = (long)(Math.random() * 1000000);
     JobParametersBuilder jobBuilder= new JobParametersBuilder();
      jobBuilder.addString("rundate", "1");
      jobBuilder.addLong("run.id", value).toJobParameters();
      jobParameters =jobBuilder.toJobParameters();

    System.out.println("jobParameters:::"+jobParameters);
	


JobExecution jobExecution = jobLauncher.run(job, jobParameters);
//JobExecution jobExecution = jobLauncherTestUtils.launchStep("step1");
System.out.println("*************************JOB Summary*****************************************");
System.out.println("Job Name : " + jobExecution.getJobInstance().getJobName());
System.out.println("Job Status : " + jobExecution.getStatus());
System.out.println("Job Start Time : " + jobExecution.getStartTime());
System.out.println("Job End Time : " + jobExecution.getEndTime());
System.out.println("Job getStepExecutions : " + jobExecution.getStepExecutions().toString());
//System.out.println("jobExecution:::"+jobExecution.getExitStatus().getExitDescription());
System.out.println("*************************End JOB Summary*****************************************");


    } catch (Exception e) {
	e.printStackTrace();
    }

  }
}