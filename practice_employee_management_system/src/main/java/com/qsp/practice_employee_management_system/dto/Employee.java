package com.qsp.practice_employee_management_system.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeId;
	//NotNull and NotBlank and Pattern only work for String type of data.
	@NotNull(message = "Name can't be null")
	@NotBlank(message = "Name can't be blank")
	@Pattern(regexp = "[a-z, A-Z]")
	private String employeeName;
	@Column(unique = true)
	@Min(value = 6000000000l)
	@Max(value = 9999999999l)
	private long employeePhone;
	@Column(unique = true)
	@NotNull(message = "Name can't be null")
	@NotBlank(message = "Name can't be blank")
	@Email(regexp = "[a-z0-9._+$]+@[a-z]+\\.[a-z]{2,3}", message="Please enter valid email")
	private String employeeEmail;
	@NotNull(message = "Name can't be null")
	@NotBlank(message = "Name can't be blank")
	private String employeePassword;
	private String employeeAddress;
	@Min(value = 1)
	private double employeeSalary;
	private String employeeGrade;
	
	//If phone is in String then:
//	@NotNull(message = "Name can't be null")
//	@NotBlank(message = "Name can't be blank")
//	@Pattern(regexp = "[6-9][0-9]{9}")
//	private String phone;
	
	//@NotEmpty : It is combination of NotNull and NotBlank
	
	//For String: NotNull, NotBlank, Pattern, NotEmpty, Email(Specifically for email)
	//For Number type data: Min, Max
	
}
