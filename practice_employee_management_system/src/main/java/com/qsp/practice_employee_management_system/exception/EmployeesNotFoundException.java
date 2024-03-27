package com.qsp.practice_employee_management_system.exception;

public class EmployeesNotFoundException extends RuntimeException{

	public EmployeesNotFoundException(String message) {
		super(message);
	}
	public String getMessage() {
		return super.getMessage();
	}
}
