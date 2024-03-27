package com.qsp.practice_employee_management_system.exception;

public class PhoneNotFoundException extends RuntimeException{
 
	public PhoneNotFoundException(String message) {
		super(message);
	}
	public String getMessage() {
		return super.getMessage();
	}
}
