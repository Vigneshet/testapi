package com.poc.testpoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poc.testpoc.service.ApiTestService;

@RestController
public class ApiTestRestController {
	
	@Autowired
	ApiTestService service;

	@GetMapping("/runTest")
	public String runTest(@RequestParam("ids") String ids) {
		System.out.println("ids ::: " + ids);
		String[] idArr = ids.split(",");
		for(String id : idArr) {
			service.callApi(id);
		}
		return "Success";
	}
	
}
