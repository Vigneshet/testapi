package com.poc.testpoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.poc.testpoc.dao.ApiTestDao;
import com.poc.testpoc.model.ApiDetails;

@Service
public class ApiTestService {

	@Autowired
	ApiTestDao dao;
	
	@Autowired
	RestTemplate template;
	
	public List<ApiDetails> getApiDetails(){
		return dao.getApiDetails();
	}
	
	public String callApi(String id) {
		ApiDetails apiDetails = dao.getApiDetail(id);
		System.out.println("apiDetails ::: " + apiDetails);
		if(apiDetails == null)
			return "Failure";
		ResponseEntity<String> response = template.getForEntity(apiDetails.getWebServiceUrl(), String.class);
		System.out.println(response.getBody());
		return response.getBody();
	}
	
	public int addApi(ApiDetails apiDetails) {
		return dao.insertApi(apiDetails);
	}
	
}
