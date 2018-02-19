package com.batch.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ResultRowMapper implements RowMapper<Result> {

	@Override
	public Result mapRow(ResultSet rs, int rowNum) throws SQLException {

		Result user = new Result();

		user.setResult(rs.getString("result"));
		

		return user;
	}

}