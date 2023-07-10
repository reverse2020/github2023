package com.v3;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.v3.record.Customer;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DataLoader {
	private static final String APP_LOADER_FILE = "static/customers.json";
	List<Customer> customers = readAppData();

	public List<Customer> getCustomers() {
		return customers;
	}
	@PostConstruct
	public void loadData() throws IOException {
		log.info("Initiating DataLoader");
		this.loadMassageIntakeAppData();
	}

	private void loadMassageIntakeAppData() throws IOException {

		long count = customers.stream().count();
		Map<String, Long> collect = customers
				.stream()
				.collect(Collectors.groupingBy(Customer::gender,Collectors.counting()));

		log.info("The JSON file has {} customers",count);
		log.info ("Stats :: {}",collect);
	}

	private List<Customer> readAppData() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
		InputStream massageIntakeAppDataInputStream = new ClassPathResource(APP_LOADER_FILE).getInputStream();
			 customers = objectMapper.readValue(massageIntakeAppDataInputStream, new TypeReference<List<Customer>>() {
			});
			return customers;
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customers;
	}

}