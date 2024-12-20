package com.day.controllers;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.day.beans.Asignacion;
import com.day.beans.Employee;
import com.day.beans.Project;
import com.day.services.IAssignmentRepository;
import com.day.services.IEmployeeRepository;
import com.day.services.IProjectRepository;

@Controller
@RequestMapping({"", "/"})
public class AssignmentsController {
	
	@Autowired
	private IProjectRepository projectRepository;
	@Autowired
	private IEmployeeRepository employeeRepository;
	@Autowired
	private IAssignmentRepository assignmentRepository;

	@GetMapping({"", "/"})
	public String getAssignmets(Model model) {
		List<Employee> employees = new ArrayList<Employee>();
		List<Project> projects = new ArrayList<Project>();
		List<List<Employee>> assignments = new ArrayList<List<Employee>>();
		List<Asignacion> asignaciones = new ArrayList<Asignacion>();
		ResultSet rs;
		
		projects = projectRepository.findAll();
		for (Project project : projects) {
			int id = project.getId();
			employees = employeeRepository.spReadEmployeesPerProject(id);
			asignaciones.add(new Asignacion(project, employees));
		}
		model.addAttribute("projects", projects);
		model.addAttribute("assignments", assignments);
		model.addAttribute("asignaciones", asignaciones);
		return "assignments/list";
	}
	
}
