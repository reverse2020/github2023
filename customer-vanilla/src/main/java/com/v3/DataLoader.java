package com.v3;

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

	@PostConstruct
	public void loadData() throws IOException {
		log.info("Initiating DataLoader");
		this.loadMassageIntakeAppData();
	}

	private void loadMassageIntakeAppData() throws IOException {

		List<Customer> readAppData = this.readAppData();
		long count = readAppData.stream().count();
		log.info("The JSON file has {} customers",count);
	}

	private List<Customer> readAppData() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		InputStream massageIntakeAppDataInputStream = new ClassPathResource(APP_LOADER_FILE).getInputStream();
		return objectMapper.readValue(massageIntakeAppDataInputStream, new TypeReference<List<Customer>>() {
		});
	}

}