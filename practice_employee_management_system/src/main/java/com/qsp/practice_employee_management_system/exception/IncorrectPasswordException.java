package com.qsp.practice_employee_management_system.exception;

public class IncorrectPasswordException extends RuntimeException{

	public IncorrectPasswordException(String message) {
		super(message);
	}
	public String getMessage() {
		return super.getMessage();
	}
}
