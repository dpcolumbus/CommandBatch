package com.batch.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

public class ValidateStoredProc extends StoredProcedure {
@Autowired
DataSource dataSource;
	
public ValidateStoredProc() {
	
}

public ValidateStoredProc(JdbcTemplate jdbcTemplate, String name) {

super(jdbcTemplate, name);
setFunction(false);

}
  public Map  validateJobCall(String param) {
	  HashMap retMap=new HashMap();
	 
	  
	  
	  JdbcTemplate jdbcTemplate= getJdbcTemplate(this.dataSource);
	  
	  ValidateStoredProc sp = new ValidateStoredProc(jdbcTemplate, "testproc");			
		SqlParameter dbid = new SqlParameter("id", Types.VARCHAR);			
		SqlParameter[] paramArray = {dbid};
		sp.setParameters(paramArray);
		sp.compile();
		
		 String[] test1=null;
		 String accountingmonth=null;
		 List<Map<String,Object>> rows=new ArrayList();
		//Call stored procedure
		Map storedProcResult = sp.execute(param);
		Map<String, String> map = new HashMap<String, String>();
		 Set set = storedProcResult.entrySet();
	      Iterator iterator = set.iterator();
	      while(iterator.hasNext()) {
	         Map.Entry mentry = (Map.Entry)iterator.next();
	         System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
	         System.out.println(mentry.getValue());
	         String values=(String) mentry.getValue().toString();
	          test1 = values.substring(2,values.length()-2).split(",");
	      }
	      for (String s : test1) {
	    	    String[] t = s.split("=");
	    	    map.put(t[0], t[1]);
	    	   
	    	}

	    	for (String s : map.keySet()) {
	    		if(s.trim().equals("rundate")) {
	    			accountingmonth=map.get(s);
	    			retMap.put("accountingMonth",accountingmonth);
	    		System.err.println(accountingmonth);
	    		}
	    		if(s.trim().equals("flag")) {
	    			String flag=map.get(s);
	    			retMap.put("flag",flag);
	    		System.err.println(flag);
	    		}
	    	 
	    	}
	    	
	    	
      
            
       
	  return retMap;
	  
  }
  public  Map  validateJobCall() {
	  HashMap retMap=new HashMap();
	 
	  	  
	  JdbcTemplate jdbcTemplate= getJdbcTemplate(this.dataSource);
		
		
		List<Map<String,Object>> rows = jdbcTemplate.queryForList("testproc 1");
            
      //SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");       
     
     
      if(rows!=null && rows.size()>0) {
      	for (Map row : rows) {
      		retMap.put("accountingMonth", row.get("rundate"));
      		retMap.put("flag", row.get("flag"));
      	}
      }
	  return retMap;
	  
  }
  
  	public JdbcTemplate getJdbcTemplate(DataSource ds) {
  	  JdbcTemplate jdbcTemplate=new JdbcTemplate(ds);
  	  return jdbcTemplate;
  	}
  	
    

}
