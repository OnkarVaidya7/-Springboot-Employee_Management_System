package com.qsp.practice_employee_management_system.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.qsp.practice_employee_management_system.util.ResponseStructure;

//Exception Handler Class

@RestControllerAdvice
//To tell JVM to go through this class instead of default exception handler. OR use @ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	//For displaying all the field exceptions in one shot
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
	
		List<ObjectError> objectErrors = ex.getAllErrors();
		Map<String, String> map = new HashMap<String, String>();
		for (ObjectError objectError : objectErrors) {
			FieldError error = (FieldError)objectError;
			String fieldName = error.getField();
			String message = error.getDefaultMessage();
			
			map.put(fieldName, message)	;		
		}
		return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
	}
	
	// Annotation will tell for which class this method should work/which class it
	// should handle
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleIdNotFoundException(IdNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Employee with the given id not found!");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(AddressNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleAddressNotFoundException(AddressNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setMessage("Employees with the given address not found!");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NameNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleNameNotFoundException(NameNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Employee with the given id not found!");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmployeesNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleEmployeesNotFoundException(EmployeesNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Employee with the given id not found!");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmailNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleEmaildNotFoundException(EmailNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Employee with the given email not found!");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IncorrectPasswordException.class)
	public ResponseEntity<ResponseStructure<String>> handleIncorrectPasswordException(IncorrectPasswordException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Incorrect Password!");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PhoneNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handlePhonedNotFoundException(PhoneNotFoundException ex) {
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Employee with the given phone not found!");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

}
