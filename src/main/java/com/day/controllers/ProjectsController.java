package com.day.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.day.beans.Asignacion;
import com.day.beans.Assignment;
import com.day.beans.Employee;
import com.day.beans.Project;
import com.day.models.ProjectDto;
import com.day.services.IAssignmentRepository;
import com.day.services.IEmployeeRepository;
import com.day.services.IProjectRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/projects")
public class ProjectsController {
	
	@Autowired
	private IAssignmentRepository assignmentRepository;
	@Autowired
	private IEmployeeRepository employeeRepository;
	@Autowired
	private IProjectRepository projectRepository;
	
	@GetMapping({"", "/"})
	public String showProjects(Model model) {
		try {
			List<Project> projects = projectRepository.findAll();
			model.addAttribute("projects", projects);
		} catch (Exception e) {
			System.out.println("showProjects() " + e.getMessage());
		}
		return "projects/list";
	}
	
	@GetMapping("/delete")
	public String deleteProject(@RequestParam int id) {
		try {
			projectRepository.deleteById(id);
		} catch (Exception e) {
			System.out.println("deleteProject() " + e.getMessage());
		}
		return "redirect:/projects";
	}
	
	@GetMapping("/create")
	public String showCreateProjectPage(Model model) {
		try {
			ProjectDto projectDto = new ProjectDto();
			model.addAttribute("projectDto", projectDto);
		} catch (Exception e) {
			System.out.println("showCreateProjectPage() " + e.getMessage());
		}
		return "projects/create";
	}
	
	
	@PostMapping("/create")
	public String createProject(@Valid @ModelAttribute ProjectDto projectDto, BindingResult result) {
		try {
			if(result.hasErrors()) {
				return "projects/create";
			}
			
			Project project = new Project();
			project.setCod(projectDto.getCod());
			project.setName(projectDto.getName());
			project.setBudget(projectDto.getBudget());
			project.setCost(projectDto.getCost());
			
			projectRepository.save(project);
		} catch (Exception e) {
			System.out.println("createProject() " + e.getMessage());
		}
		return "redirect:/projects";
	}
	
	@GetMapping("/edit")
	public String showEditProjectPage(Model model, @RequestParam int id) {
		try {
			Project project = projectRepository.findById(id).get();
			model.addAttribute("project", project);
			
			ProjectDto projectDto = new ProjectDto();
			projectDto.setCod(project.getCod());
			projectDto.setName(project.getName());
			projectDto.setBudget(project.getBudget());
			projectDto.setCost(project.getCost());
			model.addAttribute("projectDto", projectDto);

			model.addAttribute("employees", employeeRepository.findAll());
			model.addAttribute("asignaciones", assignmentEmployees(id));
		} catch (Exception e) {
			System.out.println("showEditProjectPage() " + e.getMessage());
			return "redirect:/projects";
		}
		return "projects/edit";
	}

	@PostMapping("/edit")
	public String editProject(Model model, @RequestParam int id, @Valid @ModelAttribute ProjectDto projectDto, BindingResult result) {
		try {
			Project project = projectRepository.findById(id).get();
			model.addAttribute("project", project);

			if (result.hasErrors()) {
				return "projects/edit";
			}

			project.setCod(projectDto.getCod());
			project.setName(projectDto.getName());
			project.setBudget(projectDto.getBudget());
			project.setCost(projectDto.getCost());
			projectRepository.save(project);
		} catch (Exception e) {
			System.out.println("editProject() " + e.getMessage());
		}
		return "redirect:/projects";
	}
	
	private boolean isEmployeeAsignment(int idProject, int idEmployee) {
		return assignmentRepository.fnIsEmployeeAsignment(idProject, idEmployee);
	}
	
	private List<Asignacion> assignmentEmployees(int idProject) {
		List<Asignacion> asignaciones = new ArrayList<Asignacion>();
		List<Employee> employees = employeeRepository.findAll();
		for(Employee employee: employees) {
			Asignacion asignacion = new Asignacion(employee, isEmployeeAsignment(idProject, employee.getId()));
			asignaciones.add(asignacion);
		}
		return asignaciones;
	}
	
	@GetMapping("/assign")
	public String assignEmployee(@RequestParam int idEmployee, @RequestParam int idProject) {
		try {
			Assignment assignment = new Assignment();
			assignment.setIdProject(idProject);
			assignment.setIdEmployee(idEmployee);
			assignment.setDateAssignment(Date.valueOf(LocalDate.now()));
			assignmentRepository.save(assignment);
		} catch (Exception e) {
			System.out.println("assignEmployee() " + e.getMessage());
		}
		return "redirect:/projects/edit?id="+idProject;
	}

	@GetMapping("/remove")
	public String removeEmployee(@RequestParam int idEmployee, @RequestParam int idProject) {
		try {
			int id = assignmentRepository.fnGetIdAsignment(idProject, idEmployee);
			assignmentRepository.deleteById(id);
		} catch (Exception e) {
			System.out.println("removeEmployee() " + e.getMessage());
		}
		return "redirect:/projects/edit?id="+idProject;
	}
	
}
