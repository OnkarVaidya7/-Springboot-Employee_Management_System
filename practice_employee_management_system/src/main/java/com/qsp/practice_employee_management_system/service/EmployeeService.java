package com.qsp.practice_employee_management_system.service;

import java.util.List;
import java.util.Optional;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qsp.practice_employee_management_system.dao.EmployeeDAO;
import com.qsp.practice_employee_management_system.dto.Employee;
import com.qsp.practice_employee_management_system.exception.AddressNotFoundException;
import com.qsp.practice_employee_management_system.exception.EmailNotFoundException;
import com.qsp.practice_employee_management_system.exception.EmployeesNotFoundException;
import com.qsp.practice_employee_management_system.exception.IdNotFoundException;
import com.qsp.practice_employee_management_system.exception.IncorrectPasswordException;
import com.qsp.practice_employee_management_system.exception.PhoneNotFoundException;
import com.qsp.practice_employee_management_system.util.ResponseStructure;

@Service
public class EmployeeService {

	@Autowired
	EmployeeDAO dao;
	
	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(Employee employee) {
		
		double salary = employee.getEmployeeSalary();
		if (salary<10000) {
			employee.setEmployeeGrade("D");
		}else if (salary > 10000 && salary<=20000) {
			employee.setEmployeeGrade("C");
		}else if (salary > 20000 && salary<=35000) {
			employee.setEmployeeGrade("B");
		}else {
			employee.setEmployeeGrade("A");
		}
		
//		Employee dbEmployee = dao.saveEmployee(employee);
//		if (dbEmployee != null) {
//			return "SignUp Success!";
//		} else {
//			return "Failed To SignUp!";
//		}
		
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		structure.setMessage("SignUp Success!");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.saveEmployee(employee));
		return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<List<Employee>>> saveAllEmployee(List<Employee> list) {
				
		for (Employee employee : list) {
			double salary = employee.getEmployeeSalary();
			if (salary<10000) {
				employee.setEmployeeGrade("D");
			}else if (salary > 10000 && salary<=20000) {
				employee.setEmployeeGrade("C");
			}else if (salary > 20000 && salary<=35000) {
				employee.setEmployeeGrade("B");
			}else {
				employee.setEmployeeGrade("A");
			}
		}		
//		List<Employee> dbEmployee = dao.saveAllEmployee(list);
//		if (!list.isEmpty()) {
//			return "All SignUp Success!";
//		} else {
//			return "Failed To SignUp!";
//		}
		ResponseStructure<List<Employee>> structure = new ResponseStructure<>();
		structure.setMessage("SignUp Success!");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.saveAllEmployee(list));
		return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Employee>> loginEmployee(String email, String password) {
		Employee dbEmployee = dao.loginEmployee(email, password);
		if (dbEmployee!=null) {
			if (dbEmployee.getEmployeePassword().equals(password)) {
//				return "Login Success!";
				ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
				structure.setMessage("Login Successful!");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(dbEmployee);
				return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
			} else {
//				return "Invalid Password!";
				
//				ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
//				structure.setMessage("Invalid Password!");
//				structure.setStatus(HttpStatus.OK.value());
//				structure.setData(null);
//				return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
				
				throw new IncorrectPasswordException("Incorrect Password!");
				
			}
		} else {
//			return "Please Register The Employee";
			
//			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
//			structure.setMessage("Employee Is Not Registered With The Given Email!");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(null);
//			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
			
			throw new EmailNotFoundException("Employee with the given email "+email+" not found!");
		}
	}
	
	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(int id, Employee employee) {
		Employee dbEmployee = dao.updateEmployee(id, employee);
		if (dbEmployee!=null) {
			double salary = employee.getEmployeeSalary();
			if (salary<10000) {
				employee.setEmployeeGrade("D");
			}else if (salary > 10000 && salary<=20000) {
				employee.setEmployeeGrade("C");
			}else if (salary > 20000 && salary<=35000) {
				employee.setEmployeeGrade("B");
			}else {
				employee.setEmployeeGrade("A");
			}
//			dao.saveEmployee(employee);
//			return "Updated Successfully!";
			
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			structure.setMessage("Employee Data Updated Successfully!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dao.saveEmployee(employee));
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		} else {
//			return "Unable To Update!";
			
//			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
//			structure.setMessage("Failed To Update Employee Data!");
//			structure.setStatus(HttpStatus.OK.value());
//			structure.setData(dao.saveEmployee(employee));
//			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
			
			throw new IdNotFoundException("Employee with the given id "+id+" not found!");
		}		
	}
	
	public ResponseEntity<ResponseStructure<Employee>> updateEmployeeEmail(int id, String email) {
		Optional<Employee> o =dao.updateEmployeeEmail(id, email);
		if (o.isPresent()) {
			Employee e = o.get();
			e.setEmployeeEmail(email);
//			dao.saveEmployee(e);
//			return "Email Updated!";
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			structure.setMessage("Employee Email Updated Successfully!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dao.saveEmployee(e));
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		}
//		return "Email Not Updated!";
		
//		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
//		structure.setMessage("Failed To Update Employee Email!");
//		structure.setStatus(HttpStatus.OK.value());
//		structure.setData(dao.saveEmployee(o.get()));
//		return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		
		throw new IdNotFoundException("Employee with the given id "+id+" not found!");
	}
	
	public ResponseEntity<ResponseStructure<Employee>> updateEmployeePhone(int id, long phone) {
		Optional<Employee> o = dao.updateEmployeePhone(id, phone);
		if (o.isPresent()) {
			Employee e = o.get();
			e.setEmployeePhone(phone);
//			dao.saveEmployee(e);
//			return "Phone Updated!";
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			structure.setMessage("Employee Phone Updated Successfully!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dao.saveEmployee(e));
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		}
//		return "Phone Not Updated!";
		
//		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
//		structure.setMessage("Failed To Update Employee Phone!");
//		structure.setStatus(HttpStatus.OK.value());
//		structure.setData(dao.saveEmployee(o.get()));
//		return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		
		throw new IdNotFoundException("Employee with the given id "+id+" not found!");
	}
	
	public ResponseEntity<ResponseStructure<Employee>> updateEmployeeSalary(int id, double salary) {
		Optional<Employee> o = dao.updateEmployeeSalary(id, salary);
		if (o.isPresent()) {
			Employee e = o.get();
			e.setEmployeeSalary(salary);
			if (salary<10000) {
				e.setEmployeeGrade("D");
			}else if (salary > 10000 && salary<=20000) {
				e.setEmployeeGrade("C");
			}else if (salary > 20000 && salary<=35000) {
				e.setEmployeeGrade("B");
			}else {
				e.setEmployeeGrade("A");
			}
//			dao.saveEmployee(e);
//			return "Salary Updated!";
			
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			structure.setMessage("Employee Salary Updated Successfully!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dao.saveEmployee(e));
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		}
//		return "Salary Not Updated!";
		
//		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
//		structure.setMessage("Failed To Update Employee Salary!");
//		structure.setStatus(HttpStatus.OK.value());
//		structure.setData(dao.saveEmployee(o.get()));
//		return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		
		throw new IdNotFoundException("Employee with the given id "+id+" not found!");
	}
	
	public ResponseEntity<ResponseStructure<Employee>> updateEmployeeAddress(int id, String address) {
		Optional<Employee> o = dao.updateEmployeeAddress(id, address);
		if (o.isPresent()) {
			Employee e = o.get();
			e.setEmployeeAddress(address);
//			dao.saveEmployee(e);
//			return "Address Updated!";
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			structure.setMessage("Employee Address Updated Successfully!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dao.saveEmployee(e));
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		}
//		return "Address Not Updated!";
		
//		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
//		structure.setMessage("Failed To Update Employee Address!");
//		structure.setStatus(HttpStatus.OK.value());
//		structure.setData(dao.saveEmployee(o.get()));
//		return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		
		throw new IdNotFoundException("Employee with the given id "+id+" not found!");
		
	}
	
	public ResponseEntity<ResponseStructure<Employee>> updateEmployeePhoneByPhone(long oldPhone, long newPhone) {
		Employee e = dao.updateEmployeePhoneByPhone(oldPhone, newPhone);
		if (e != null){
			
			e.setEmployeePhone(newPhone);
//			dao.saveEmployee(e);
//			return "Phone updated successfully!";
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			structure.setMessage("Employee Phone Updated Successfully!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dao.saveEmployee(e));
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
			
		}
//		return "Employee not present!";
		
//		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
//		structure.setMessage("Employee Not Present!");
//		structure.setStatus(HttpStatus.NOT_FOUND.value());
//		structure.setData(dao.saveEmployee(e));
//		return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
		
		throw new PhoneNotFoundException("Employee with the given phone "+oldPhone+" not found!");
	}
	
	public ResponseEntity<ResponseStructure<Employee>> updateEmployeeEmailByEmail(String oldEmail, String newEmail) {
		Employee e = dao.updateEmployeeEmailByEmail(oldEmail, newEmail);
		if (e != null){
			
			e.setEmployeeEmail(newEmail);
//			dao.saveEmployee(e);
//			return "Phone updated successfully!";
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			structure.setMessage("Employee Email Updated Successfully!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dao.saveEmployee(e));
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		}
//		return "Employee not present!";
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		structure.setMessage("Employee Not Present!");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(dao.saveEmployee(e));
		return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<ResponseStructure<Employee>> findEmployeeById(int id) {
		Optional<Employee> o = dao.findEmployeeById(id);
		if (o.isPresent()) {
//			return o.get();
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			structure.setMessage("Employee Found!");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(o.get());
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);
		}
//		return null;
//		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
//		structure.setMessage("Employee Not Found!");
//		structure.setStatus(HttpStatus.NOT_FOUND.value());
//		structure.setData(null);
//		return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
		
		throw new IdNotFoundException("Employee with the given id "+id+" not found!");
	}
	
	public ResponseEntity<ResponseStructure<List<Employee>>> findAllEmployee() {
//		return dao.findAllEmployee();
		List<Employee> e = dao.findAllEmployee();
		if (!e.isEmpty()) {
			ResponseStructure<List<Employee>> structure = new ResponseStructure<>();
			structure.setMessage("Employees Found!");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(e);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.FOUND);
		}
//		ResponseStructure<List<Employee>> structure = new ResponseStructure<>();
//		structure.setMessage("Employees Not Found!");
//		structure.setStatus(HttpStatus.NOT_FOUND.value());
//		structure.setData(e);
//		return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.NOT_FOUND);
		
		throw new EmployeesNotFoundException("There are no employees present!");
	}
	
	public ResponseEntity<ResponseStructure<Employee>> findEmployeeByPhone(long phone) {
//		return dao.findEmployeeByPhone(phone);
		Employee e = dao.findEmployeeByPhone(phone);
		if (e != null) {
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			structure.setMessage("Employee Found!");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(e);
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);
		}
//		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
//		structure.setMessage("Employee Not Found!");
//		structure.setStatus(HttpStatus.NOT_FOUND.value());
//		structure.setData(e);
//		return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
		
		throw new PhoneNotFoundException("Employee with the given phone "+phone+" not found!");
	}
	
	public ResponseEntity<ResponseStructure<Employee>> findEmployeeByEmail(String email) {
//		return dao.findEmployeeByEmail(email);
		Employee e = dao.findEmployeeByEmail(email);
		if (e!=null) {
			ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
			structure.setMessage("Employee Found!");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(e);
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);
		}
//		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
//		structure.setMessage("Employee Not Found!");
//		structure.setStatus(HttpStatus.NOT_FOUND.value());
//		structure.setData(e);
//		return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
		
		throw new EmailNotFoundException("Employee with the given email "+email+" not found!");
	}
	
	public ResponseEntity<ResponseStructure<List<Employee>>> findEmployeeByName(String name) {
//		return dao.findEmployeeByName(name);
		List<Employee> e = dao.findEmployeeByName(name);
		if (!e.isEmpty()) {
			ResponseStructure<List<Employee>> structure = new ResponseStructure<>();
			structure.setMessage("Employees Found!");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(e);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.FOUND);
		}
//		ResponseStructure<List<Employee>> structure = new ResponseStructure<>();
//		structure.setMessage("Employees Not Found!");
//		structure.setStatus(HttpStatus.NOT_FOUND.value());
//		structure.setData(e);
//		return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.NOT_FOUND);
		
		throw new EmployeesNotFoundException("Employees with the given name "+name+" not found!");
	}
	
	public ResponseEntity<ResponseStructure<List<Employee>>> findEmployeeByAddress(String address) {
//		return dao.findEmployeeByAddress(address);
		List<Employee> e = dao.findEmployeeByAddress(address);
		if (!e.isEmpty()) {
			ResponseStructure<List<Employee>> structure = new ResponseStructure<>();
			structure.setMessage("Employees Found!");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(e);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.FOUND);
		}
//		ResponseStructure<List<Employee>> structure = new ResponseStructure<>();
//		structure.setMessage("Employees Not Found!");
//		structure.setStatus(HttpStatus.NOT_FOUND.value());
//		structure.setData(e);
//		return new ResponseEntity<ResponseStructure<List<Employee>>>(structure, HttpStatus.NOT_FOUND);
		
		throw new AddressNotFoundException("Employees with the given address "+address+" not found!");
	}
	
	public ResponseEntity<ResponseStructure<Employee>> deleteEmployee(int id) {
		Employee employee = dao.deleteEmployee(id);
		if (employee != null) {
//			return "Employee Deleted Successfully!";
			ResponseStructure<Employee> structure = new ResponseStructure<>();
			structure.setMessage("Employee Deleted!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
			
		}
//		return "Employee Not Present!";
		
//		ResponseStructure<Employee> structure = new ResponseStructure<>();
//		structure.setMessage("Employee Not Found!");
//		structure.setStatus(HttpStatus.NOT_FOUND.value());
//		structure.setData(null);
//		return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
		
		throw new IdNotFoundException("Employee with the given id "+id+" not found!");
	}
	
	public ResponseEntity<ResponseStructure<Employee>> deleteEmployeeByEmail(String email) {
		Employee employee = dao.deleteEmployeeByEmail(email);
		if (employee != null) {
//			return "Employee Deleted Successfully!";
			ResponseStructure<Employee> structure = new ResponseStructure<>();
			structure.setMessage("Employee Deleted!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		}
//		return "Employee Not Present!";
		
//		ResponseStructure<Employee> structure = new ResponseStructure<>();
//		structure.setMessage("Employee Not Found!");
//		structure.setStatus(HttpStatus.NOT_FOUND.value());
//		structure.setData(null);
//		return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
		
		throw new EmailNotFoundException("Employee with the given email "+email+" not found!");
	}
	
	public ResponseEntity<ResponseStructure<Employee>> deleteEmployeeByPhone(long phone) {
		Employee employee = dao.deleteEmployeeByPhone(phone);
		if (employee != null) {
//			return "Employee Deleted Successfully!";
			ResponseStructure<Employee> structure = new ResponseStructure<>();
			structure.setMessage("Employee Deleted!");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
		}
//		return "Employee Not Present!";
		
//		ResponseStructure<Employee> structure = new ResponseStructure<>();
//		structure.setMessage("Employee Not Found!");
//		structure.setStatus(HttpStatus.NOT_FOUND.value());
//		structure.setData(null);
//		return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.NOT_FOUND);
		
		throw new PhoneNotFoundException("Employee with the given phone "+phone+" not found!");
	}
}
