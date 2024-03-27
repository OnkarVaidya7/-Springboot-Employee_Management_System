package com.qsp.practice_employee_management_system.exception;

public class NameNotFoundException extends RuntimeException{

	public NameNotFoundException(String message) {
		super(message);
	}
	public String getMessage() {
		return super.getMessage();
	}
}
