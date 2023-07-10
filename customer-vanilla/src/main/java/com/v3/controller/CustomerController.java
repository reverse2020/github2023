package com.v3.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v3.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService service;
	
	@GetMapping
	public Map<String,Object> getStats(){
		return service.getStats();
	}

}
