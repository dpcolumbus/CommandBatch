package com.batch.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class SetupRowMapper implements RowMapper<Setup> {

	@Override
	public Setup mapRow(ResultSet rs, int rowNum) throws SQLException {

		Setup user = new Setup();
		System.err.println("ffjkfdgjdkflgjldglg"+user);
		//user.setRunDate(rs.getDate("rundate"));
		

		return user;
	}

}