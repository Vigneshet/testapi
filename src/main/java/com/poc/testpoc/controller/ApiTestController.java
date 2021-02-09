package com.poc.testpoc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.poc.testpoc.model.ApiDetails;
import com.poc.testpoc.service.ApiTestService;

@Controller
public class ApiTestController {

	
	@Autowired
	ApiTestService service;
	
	@GetMapping("/index")
	public String showApiList(Model model) {
		
		/*
		 * List<ApiDetails> apiList = new ArrayList<>(); ApiDetails api = new
		 * ApiDetails(); api.setId(1); api.setWebServiceName("Test");
		 * api.setWebServiceUrl("https://jsonplaceholder.typicode.com/users");
		 * api.setUsername("check"); api.setPassword("check"); apiList.add(api);
		 */
	    model.addAttribute("apis", service.getApiDetails());
	    return "index";
	}
	
	
}
