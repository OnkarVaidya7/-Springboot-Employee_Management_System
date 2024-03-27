package com.qsp.practice_employee_management_system.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qsp.practice_employee_management_system.dto.Employee;

public interface EmployeRepo extends JpaRepository<Employee, Integer>{

	@Query("Select e From Employee e Where e.employeeEmail=?1")
	Employee findEmployeeByEmail(String employeeEmail);
	
	Employee findByEmployeePhone(long employeePhone);
	Employee findByEmployeeEmail(String employeeEmail);
	List<Employee> findByEmployeeName(String employeeName);
	List<Employee> findByEmployeeAddress(String employeeAddress);


}
