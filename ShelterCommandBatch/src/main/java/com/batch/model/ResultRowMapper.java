package com.batch.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ResultRowMapper implements RowMapper<FinalRow> {

	@Override
	public FinalRow mapRow(ResultSet rs, int rowNum) throws SQLException {

		FinalRow finalRow = new FinalRow();

		finalRow.setFinalRow(rs.getString("FinalRow"));
		

		return finalRow;
	}

}