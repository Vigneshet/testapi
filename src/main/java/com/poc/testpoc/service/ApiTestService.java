package com.poc.testpoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.testpoc.dao.ApiTestDao;
import com.poc.testpoc.model.ApiDetails;

@Service
public class ApiTestService {

	@Autowired
	ApiTestDao dao;
	
	
	public List<ApiDetails> getApiDetails(){
		/*
		 * List<ApiDetails> list = dao.getApiDetails(); System.out.println(list);
		 * if(list!=null && list.size()>0) { System.out.println(list.size());
		 * for(ApiDetails api : list) { System.out.println(api.getWebServiceUrl()); } }
		 */
		return dao.getApiDetails();
	}
	
}
