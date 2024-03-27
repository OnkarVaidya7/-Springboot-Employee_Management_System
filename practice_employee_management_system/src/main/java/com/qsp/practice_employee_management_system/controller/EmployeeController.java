package com.qsp.practice_employee_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.practice_employee_management_system.dto.Employee;
import com.qsp.practice_employee_management_system.service.EmployeeService;
import com.qsp.practice_employee_management_system.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService service;
	
	@PostMapping("/signup")
	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(@Valid @RequestBody Employee employee) {
		return service.saveEmployee(employee);
	}
	
	@PostMapping("/signup/all")
	public ResponseEntity<ResponseStructure<List<Employee>>> saveAllEmployee(@Valid @RequestBody List<Employee> list) {
		return service.saveAllEmployee(list);
	}
	
	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<Employee>> loginEmployee(@Valid @RequestParam String email, @RequestParam String password) {
		return service.loginEmployee(email, password);
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(@RequestParam int id, @RequestBody Employee employee) {
		return service.updateEmployee(id, employee);
	}
	
	@PatchMapping("/update/email")
	public ResponseEntity<ResponseStructure<Employee>> updateEmployeeEmail(@RequestParam int id ,@RequestParam String email) {
		return service.updateEmployeeEmail(id, email);
	}
	
	@PatchMapping("/update/phone")
	public ResponseEntity<ResponseStructure<Employee>> updateEmployeePhone(@RequestParam int id, @RequestParam long phone) {
		return service.updateEmployeePhone(id, phone);
	}
	
	@PatchMapping("/update/salary")
	public ResponseEntity<ResponseStructure<Employee>> updateEmployeeSalary(@RequestParam int id, @RequestParam double salary) {
		return service.updateEmployeeSalary(id, salary);
	}
	
	@PatchMapping("update/address")
	public ResponseEntity<ResponseStructure<Employee>> updateEmployeeAddress(@RequestParam int id, @RequestParam String address) {
		return service.updateEmployeeAddress(id, address);
	}
	
	@PatchMapping("/update/old/newphone")
	public ResponseEntity<ResponseStructure<Employee>> updateEmployeePhoneByPhone(@RequestParam long oldPhone, @RequestParam long newPhone) {
		return service.updateEmployeePhoneByPhone(oldPhone, newPhone);
	}
	
	@PatchMapping("/update/old/newemail")
	public ResponseEntity<ResponseStructure<Employee>> updateEmployeeEmailByEmail(String oldEmail, String newEmail) {
		return service.updateEmployeeEmailByEmail(oldEmail, newEmail);
	}
	
	@GetMapping("/find")
	public ResponseEntity<ResponseStructure<Employee>> findEmployeeById(int id) {
		return service.findEmployeeById(id);
	}
	
	@GetMapping("/find/all")
	public ResponseEntity<ResponseStructure<List<Employee>>> findAllEmployee() {
		return service.findAllEmployee();
	}
	
	@GetMapping("/find/phone")
	public ResponseEntity<ResponseStructure<Employee>> findEmployeeByPhone(long phone) {
		return service.findEmployeeByPhone(phone);
	}
	
	@GetMapping("/find/email")
	public ResponseEntity<ResponseStructure<Employee>> findEmployeeByEmail(String email) {
		return service.findEmployeeByEmail(email);
	}
	
	@GetMapping("/find/name")
	public ResponseEntity<ResponseStructure<List<Employee>>> findEmployeeByName(String name) {
		return service.findEmployeeByName(name);
	}
	
	@GetMapping("/find/address")
	public ResponseEntity<ResponseStructure<List<Employee>>> findEmployeeByAddress(String address) {
		return service.findEmployeeByAddress(address);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseStructure<Employee>> deleteEmployee(int id) {
		return service.deleteEmployee(id);
	}
	
	@DeleteMapping("/delete/email")
	public ResponseEntity<ResponseStructure<Employee>> deleteEmployeeByEmail(String email) {
		return service.deleteEmployeeByEmail(email);
	}
	
	@DeleteMapping("/delete/phone")
	public ResponseEntity<ResponseStructure<Employee>> deleteEmployeeByPhone(long phone) {
		return service.deleteEmployeeByPhone(phone);
	}
}
