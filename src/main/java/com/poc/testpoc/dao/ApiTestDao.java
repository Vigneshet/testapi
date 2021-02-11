package com.poc.testpoc.dao;

import java.util.List;

import com.poc.testpoc.model.ApiDetails;

public interface ApiTestDao {

	public List<ApiDetails> getApiDetails();
	
	public ApiDetails getApiDetail(String id);
	
	public int insertApi(ApiDetails apiDetails);
	
	public boolean isTableThere(String tableName);
	
	public void createTable(String sql);
	
	public void insertRecord(List<String> sql);
	
}
