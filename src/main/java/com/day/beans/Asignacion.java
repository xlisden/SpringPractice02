package com.day.beans;

import java.util.List;

public class Asignacion {

	private Project project;
	private List<Employee> employees;
	private Employee employee;
	private boolean isAssignment;

	public Asignacion() {
	}

	public Asignacion(Project project, List<Employee> employees) {
		this.project = project;
		this.employees = employees;
	}

	public Asignacion(Employee employee, boolean isAssignment) {
		this.employee = employee;
		this.isAssignment = isAssignment;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public boolean isAssignment() {
		return isAssignment;
	}

	public void setAssignment(boolean isAssignment) {
		this.isAssignment = isAssignment;
	}

	@Override
	public String toString() {
		return "Asignacion [employee=" + employee + ", isAssignment=" + isAssignment + "]";
	}

}
