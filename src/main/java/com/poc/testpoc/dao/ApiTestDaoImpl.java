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

}
