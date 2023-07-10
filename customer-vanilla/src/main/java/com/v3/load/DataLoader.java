package com.v3.load;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
	List<Customer> customers;

	public List<Customer> getCustomers() {
		return customers;
	}

	@PostConstruct
	public void loadData() throws IOException {
		log.info("Initiating DataLoader");
		customers = readAppData();
	}

	private List<Customer> readAppData() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			InputStream massageIntakeAppDataInputStream = new ClassPathResource(APP_LOADER_FILE).getInputStream();
			customers = objectMapper.readValue(massageIntakeAppDataInputStream, new TypeReference<List<Customer>>() {
			});
			return customers;

		} catch (IOException e) {
			log.error("Exception Occured :: {}", e.getMessage());
			e.printStackTrace();
		}
		return customers;
	}

}