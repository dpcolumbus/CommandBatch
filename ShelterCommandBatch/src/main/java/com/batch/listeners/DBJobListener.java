package com.batch.listeners;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;


import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;

public class DBJobListener implements JobExecutionListener  {
	@Autowired
	DataSource dataSource;
	@Override
	public void beforeJob(JobExecution jobExecution) {
	
		
		

      	
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void afterJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		
	}

	

	
}


