package com.qsp.practice_employee_management_system.exception;

public class AddressNotFoundException extends RuntimeException{

	public AddressNotFoundException(String message) {
		super(message);
	}
	public String getMessage() {
		return super.getMessage();
	}
}
