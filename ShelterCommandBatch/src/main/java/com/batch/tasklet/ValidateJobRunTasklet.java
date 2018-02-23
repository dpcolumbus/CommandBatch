package com.batch.tasklet;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.sql.DataSource;


import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.CallableStatementCreatorFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.util.StringUtils;




public class ValidateJobRunTasklet implements Tasklet{
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public RepeatStatus execute(StepContribution arg0, ChunkContext chunkContext) throws Exception {
		// TODO Auto-generated method stub

		ExecutionContext stepContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
		String status= (String) chunkContext.getStepContext().getJobParameters().get("status");
		
		 System.out.println("jobParameters:::"+chunkContext.getStepContext().getJobParameters().toString());
		
		SimpleJdbcCall jdbcCall =  new SimpleJdbcCall(jdbcTemplate).withProcedureName("get_accounting_month");
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("Status", String.valueOf(status));
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);


		Map<String, Object> storedProcResult = jdbcCall.execute(in);
		Map<String, String> resultMap = new HashMap<String, String>();
			String[] resultArr=null;
			 String accountingmonth=null;
			 String isProcessed=null;
		
		 Iterator<Entry<String, Object>> it = storedProcResult.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
		        String key = (String) entry.getKey();
		        String values = (String) entry.getValue().toString();
		        //System.out.println("Key: "+key);
		       // System.out.println("Value: "+values);
		        if(values!=null) {
		        	resultArr = values.substring(2,values.length()-2).split(",");
			      }
			      for (String res : resultArr) {
			    	    String[] result = res.split("=");
			    	    if(result.length>1 && result[0]!=null && !StringUtils.isEmpty(result[1]) )
			    	    	resultMap.put(result[0], result[1]);
			    	   
			    		}

			    	for (String resultKey : resultMap.keySet()) {
			    		if(resultKey.trim().equals("SSISAccountingMonth")) {
			    			accountingmonth=resultMap.get(resultKey);
			    	//	System.err.println(accountingmonth);
			    		}
			    		if(resultKey.trim().equals("IsProcessed")) {
			    		
			    			isProcessed=resultMap.get(resultKey);
			    	//	System.err.println(isProcessed);
			    		}
			    	    
			    	}
			    	
			    
		        }
		    
         			try {
         			  if(isProcessed.trim().equals("True")) {  
         				 stepContext.put("accountingmonth",accountingmonth);  
         				String jobName= (String) chunkContext.getStepContext().getJobParameters().get("JobMethodName");
         				 stepContext.put("jobMethodName",jobName);
         				chunkContext.getStepContext().getStepExecution().setExitStatus(new ExitStatus("COMPLETED"));
         			  	}else {         			  		
         			chunkContext.getStepContext().getStepExecution().setExitStatus(new ExitStatus("StopJobExecution"));
         			  	}
         			}catch(Exception e) {
         				 chunkContext.getStepContext().getStepExecution().setExitStatus(new ExitStatus("StopJobExecution"));
         			  }
       
		return RepeatStatus.FINISHED;
	}
	


	
	
}
