package com.poc.testpoc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.poc.testpoc.constants.ApiTestConstants;
import com.poc.testpoc.model.ApiDetails;

@Component
public class ApiTestDaoImpl implements ApiTestDao {

	@Autowired
	JdbcTemplate template;

	@Override
	public List<ApiDetails> getApiDetails() {
		return template.query(ApiTestConstants.GET_API_DETAILS, new BeanPropertyRowMapper<>(ApiDetails.class));
	}

	@SuppressWarnings("deprecation")
	@Override
	public ApiDetails getApiDetail(String id) {
		return template.queryForObject(ApiTestConstants.GET_API_DETAILS_SPECIFIC, new Object[] { id },
				new BeanPropertyRowMapper<>(ApiDetails.class));
	}

	@Override
	public int insertApi(ApiDetails apiDetails) {
		return template.update(ApiTestConstants.INSERT_API_DETAILS, new Object[] { apiDetails.getWebServiceName(),
				apiDetails.getWebServiceUrl(), apiDetails.getUsername(), apiDetails.getPassword() });
	}

	@Override
	public boolean isTableThere(String tableName) {
		int count = 0;
		try {
			count = template.queryForObject(ApiTestConstants.SELECT_TABLE + tableName, Integer.class);	
		}catch(Exception e) {
			//System.out.println(e.getMessage());
		}
		return count > 0 ? true : false;
	}

	@Override
	public void createTable(String sql) {
		System.out.println("Inside create table :: " + sql);
		template.execute(sql);
	}

	@Override
	public int insertRecord(String sql) {
		System.out.println("inside insert");
		return 0;
	}

}
