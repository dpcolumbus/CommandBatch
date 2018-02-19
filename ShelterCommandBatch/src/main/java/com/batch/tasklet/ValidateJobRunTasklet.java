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

import com.batch.dao.ValidateStoredProc;
import com.batch.model.Setup;
import com.batch.model.SetupRowMapper;

public class ValidateJobRunTasklet implements Tasklet{
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	

	// The result sets of the stored procedure
	
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		// TODO Auto-generated method stub
		//System.err.println("calling ValidateJobRunTasklet");
		
		ExecutionContext stepContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
		String status= (String) chunkContext.getStepContext().getJobParameters().get("status");
		
		 System.out.println("jobParameters:::"+chunkContext.getStepContext().getJobParameters().toString());
		//JdbcTemplate myJDBC=new JdbcTemplate(this.dataSource);
		//call(myJDBC, id);
		SimpleJdbcCall jdbcCall =  new SimpleJdbcCall(jdbcTemplate).withProcedureName("get_accounting_month");
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("status", String.valueOf(status));
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);


		Map<String, Object> storedProcResult = jdbcCall.execute(in);
		Map<String, String> resultMap = new HashMap<String, String>();
			String[] resultArr=null;
			 String accountingmonth=null;
			 String flag=null;
		
		 Iterator<Entry<String, Object>> it = storedProcResult.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
		        String key = (String) entry.getKey();
		        String values = (String) entry.getValue().toString();
		        System.out.println("Key: "+key);
		        System.out.println("Value: "+values);
		        if(values!=null) {
		        	resultArr = values.substring(2,values.length()-2).split(",");
			      }
			      for (String res : resultArr) {
			    	    String[] result = res.split("=");
			    	    resultMap.put(result[0], result[1]);
			    	   
			    	}

			    	for (String resultKey : resultMap.keySet()) {
			    		if(resultKey.trim().equals("rundate")) {
			    			accountingmonth=resultMap.get(resultKey);
			    		System.err.println(accountingmonth);
			    		}
			    		if(resultKey.trim().equals("flag")) {
			    			flag=resultMap.get(resultKey);
			    		System.err.println(flag);
			    		}
			    	    
			    	}
		        }
		    
		 
        
         		if(flag.trim().equals("1") ) {
         			//System.err.println(" tasklet flag is if :::");
         			  stepContext.put("accountingmonth","'"+accountingmonth+"'");
         			  if(status.trim().equals("1")) {
         				 stepContext.put("proc","GetAllRecord_ACH");
         			  }else {
         				 stepContext.put("proc","ODS_Output_Job");
         			  }
         			
         		chunkContext.getStepContext().getStepExecution().setExitStatus(new ExitStatus("COMPLETED"));
         		}else {
         			//System.err.println(" tasklet flag is else :::");
         			chunkContext.getStepContext().getStepExecution().setExitStatus(new ExitStatus("SKIPJOB"));
         		}
            
       
		return null;
	}

	
	
	
	public static Map<String, Object> call(JdbcTemplate jdbcTemplate,
            String param0
           ) {
final Map<String, Object> actualParams = new HashMap<String,Object>();
actualParams.put("id", param0);
String sql = "{call testproc(?)}";
 List<SqlParameter> returnedParams = new ArrayList();
// The input parameters of the stored procedure
List<SqlParameter> declaredParams = Arrays.asList(
    new SqlParameter("id", Types.VARCHAR)
   );

 CallableStatementCreatorFactory cscFactory
    = new CallableStatementCreatorFactory(sql, declaredParams);

CallableStatementCreator csc = cscFactory.newCallableStatementCreator(actualParams);
Map<String, Object> results = jdbcTemplate.call(csc, returnedParams);



return results;
}
}
