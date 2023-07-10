package com.v3.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.v3.load.DataLoader;
import com.v3.record.Customer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerService {
	
	@Autowired
	DataLoader dataLoader;
	
	public Map<String, Object> getStats() {
		Map<String, Object> stats =new HashMap<>();
		List<Customer> customers = dataLoader.getCustomers();
		Map<String, Long> collect = customers
				.stream()
				.collect(Collectors.groupingBy(Customer::gender,Collectors.counting()));

		log.info("The JSON file has {} customers",customers.stream().count());
		log.info ("Stats :: {}",collect);
		stats.put("The JSON file has customers # :: ", customers.stream().count());
		stats.put("Group by Gender :: ", collect);
		return stats;
	}

}
