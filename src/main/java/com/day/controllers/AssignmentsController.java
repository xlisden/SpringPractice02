package com.day.controllers;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.day.beans.Employee;
import com.day.beans.Project;
import com.day.services.IEmployeeRepository;
import com.day.services.IProjectRepository;

@Controller
@RequestMapping("")
public class AssignmentsController {
	
	@Autowired
	private IProjectRepository projectRepository;
	@Autowired
	private IEmployeeRepository employeeRepository;

	@GetMapping({"", "/"})
	public String getAssignmets(Model model) {
		List<Employee> employees = new ArrayList<Employee>();
		List<Project> projects = new ArrayList<Project>();
		ResultSet rs;
		
		projects = projectRepository.findAll();
		for (Project project : projects) {
			int id = project.getId();
			employees = employeeRepository.spReadEmployeesPerProject(id);
			for (Employee employee : employees) {
				System.out.println(employee.getId() + " " + employee.getCod() + " " + employee.getLastName());
			}
			break;
		}
		return "assignments/list";
	}
	
}
