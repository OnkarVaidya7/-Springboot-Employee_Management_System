package com.qsp.practice_employee_management_system.exception;

public class IdNotFoundException extends RuntimeException{

	public IdNotFoundException(String message) {
		super(message);
	}
	
	public String getMessage() {
		return super.getMessage();
	}
}
