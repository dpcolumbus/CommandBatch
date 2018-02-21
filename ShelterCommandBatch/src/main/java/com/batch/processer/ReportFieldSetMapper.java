package com.batch.processer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;




public class ReportFieldSetMapper implements FieldSetMapper {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public Object mapFieldSet(FieldSet arg0) throws BindException {
		// TODO Auto-generated method stub
		
		System.out.println("called Field Set mapper");
		return null;
	}
	
	

}