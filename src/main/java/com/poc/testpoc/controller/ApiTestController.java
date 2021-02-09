package com.poc.testpoc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
		 * api.setUsername("check"); api.setPassword("check"); ApiDetails api1 = new
		 * ApiDetails(); api1.setId(2); api1.setWebServiceName("Test");
		 * api1.setWebServiceUrl("https://jsonplaceholder.typicode.com/users");
		 * api1.setUsername("check"); api1.setPassword("check"); ApiDetails api2 = new
		 * ApiDetails(); api2.setId(3); api2.setWebServiceName("Test");
		 * api2.setWebServiceUrl("https://jsonplaceholder.typicode.com/users");
		 * api2.setUsername("check"); api2.setPassword("check"); apiList.add(api);
		 * apiList.add(api1); apiList.add(api2);
		 */
		model.addAttribute("apis", service.getApiDetails());
		//model.addAttribute("apis", apiList);
		return "index";
	}
	
	@PostMapping("/addapi")
	public String addApi(ApiDetails apiDetails , BindingResult result, Model model) {
		System.out.println("inside add api :: " + apiDetails);
		service.addApi(apiDetails);
		return "redirect:/index";
	}
	
	@GetMapping("/addapiview")
	public String addApiView(ApiDetails apiDetails) {
		return "addapi";
	}

}
