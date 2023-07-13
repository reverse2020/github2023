package com.v3.exception;

import lombok.Data;


@SuppressWarnings("serial")
@Data
public class CustomerNotFoundException extends RuntimeException{

	private String errorCode;

	public CustomerNotFoundException(String message,String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
}
