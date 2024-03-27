package com.qsp.practice_employee_management_system.exception;

public class EmailNotFoundException extends RuntimeException{

	public EmailNotFoundException(String message){
		super(message);
	}
	public String getMessage() {
		return super.getMessage();
	}
}
