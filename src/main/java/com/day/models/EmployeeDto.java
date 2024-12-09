package com.day.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class EmployeeDto {

	@NotBlank(message = "The name is required.")
	private String name;
	@NotEmpty(message = "The last name is required.")
	private String lastName;
	@NotEmpty(message = "The cod is required.")
	private String cod;
	@NotEmpty(message = "The department is required.")
	private String department;
	@NotEmpty(message = "The position is required.")
	
	private String position;

	public EmployeeDto() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
