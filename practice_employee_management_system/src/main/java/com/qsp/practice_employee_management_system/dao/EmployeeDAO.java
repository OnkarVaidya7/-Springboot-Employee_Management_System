package com.qsp.practice_employee_management_system.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qsp.practice_employee_management_system.dto.Employee;
import com.qsp.practice_employee_management_system.repo.EmployeRepo;

@Repository
public class EmployeeDAO {

	@Autowired
	EmployeRepo repo;

	public Employee saveEmployee(Employee employee) {
		return repo.save(employee);
	}

	public List<Employee> saveAllEmployee(List<Employee> employee) {
		return repo.saveAll(employee);
	}

	public Employee loginEmployee(String email, String password) {
		return repo.findEmployeeByEmail(email);
	}

	public Optional<Employee> findEmployeeById(int id) {
		return repo.findById(id);
	}

	public List<Employee> findAllEmployee() {
		return repo.findAll();
	}

	public Employee findEmployeeByPhone(long phone) {
		return repo.findByEmployeePhone(phone);
	}

	public Employee findEmployeeByEmail(String email) {
		return repo.findByEmployeeEmail(email);
	}

	public List<Employee> findEmployeeByName(String name) {
		return repo.findByEmployeeName(name);
	}

	public List<Employee> findEmployeeByAddress(String address) {
		return repo.findByEmployeeAddress(address);
	}

	public Employee updateEmployee(int id, Employee employee) {
		Optional<Employee> dbEmployee = repo.findById(id);
		if (dbEmployee != null) {
			employee.setEmployeeId(id);
			return repo.save(employee);
		} else {
			return null;
		}
	}

	public Optional<Employee> updateEmployeeEmail(int id, String email) {
		return repo.findById(id);

	}

	public Employee updateEmployeeEmailByEmail(String oldEmail, String newEmail) {
//		Employee e = repo.findByEmployeeEmail(oldEmail);
//		if (e != null){
//			
//			e.setEmployeeEmail(newEmail);
//			repo.save(e);
//			return "Email updated successfully!";
//		}else {
//			return "Employee not present!";
//		}
		return repo.findByEmployeeEmail(oldEmail);
	}

	public Optional<Employee> updateEmployeePhone(int id, long phone) {
		return repo.findById(id);
	}

	public Employee updateEmployeePhoneByPhone(long oldPhone, long newPhone) {
//		Employee e = repo.findByEmployeePhone(oldPhone);
//		if (e != null){
//			
//			e.setEmployeePhone(newPhone);
//			repo.save(e);
//			return "Phone updated successfully!";
//		}else {
//			return "Employee not present!";
//		}
		return repo.findByEmployeePhone(oldPhone);

	}

	public Optional<Employee> updateEmployeeSalary(int id, double salary) {
		return repo.findById(id);
	}

	public Optional<Employee> updateEmployeeAddress(int id, String address) {
		return repo.findById(id);
	}

	public Employee deleteEmployee(int id) {
		Optional<Employee> o = repo.findById(id);
		if (o.isPresent()) {
			Employee employee = o.get();
			repo.delete(employee);
			return employee;
		}
		return null;
	}

	public Employee deleteEmployeeByEmail(String email) {
		Employee employee = repo.findEmployeeByEmail(email);
		if (employee != null) {
			repo.delete(employee);
			return employee;
		}
		return null;
	}

	public Employee deleteEmployeeByPhone(long phone) {
		Employee employee = repo.findByEmployeePhone(phone);
		if (employee != null) {
			repo.delete(employee);
			return employee;
		}
		return null;
	}
}
