package com.poc.testpoc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.poc.testpoc.model.ApiDetails;

@Component
public class ApiTestDaoImpl implements ApiTestDao {
	
	@Autowired
	JdbcTemplate template;

	@Override
	public List<ApiDetails> getApiDetails() {
		String sql = "Select id as id, web_service_name as webservicename, web_service_url as webserviceurl, user_name as username, password as password from webservice_details";
		return template.query(sql,new BeanPropertyRowMapper<>(ApiDetails.class));
	}

}
