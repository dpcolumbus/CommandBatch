package com.batch.tasklet;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class ProcessDBDataTasklet implements Tasklet{
	@Autowired
	DataSource dataSource;
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		// TODO Auto-generated method stub
		 ExecutionContext stepContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
		 System.err.println("run date for the job is :::::"+stepContext.get("rundate"));
		JdbcTemplate myJDBC=new JdbcTemplate(this.dataSource);
        // myJDBC.execute("testproc");
        List rows = myJDBC.queryForList("getData");
       /* if(rows!=null && rows.size()>0) {
        	chunkContext.getStepContext().getStepExecution().setExitStatus(new ExitStatus("COMPLETED"));
        }else {
        	chunkContext.getStepContext().getStepExecution().setExitStatus(new ExitStatus("SKIPJOB"));
        }*/
		return null;
	}

}
