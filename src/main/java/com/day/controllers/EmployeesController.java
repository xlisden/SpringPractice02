package com.day.controllers;

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

import com.day.beans.Employee;
import com.day.models.EmployeeDto;
import com.day.services.IEmployeeRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/employees")
public class EmployeesController {
	
	@Autowired
	private IEmployeeRepository employeeRepository;
	
	@GetMapping( {"", "/"} )
	public String showEmployeesList(Model model) {
		List<Employee> employees = employeeRepository.findAll();
		model.addAttribute("employees", employees);
		return "employees/list";
	}
	
	@GetMapping( {"/delete"} )
	public String deleteEmployee(@RequestParam int id) {
		try {
			employeeRepository.deleteById(id);
		} catch (Exception e) {
			System.out.println("deleteEmployee() " + e.getMessage());
		}
		return "redirect:/employees";
	}

	@GetMapping( {"/create"} )
	public String showCreateEmployeePage(Model model) {
		try {
			EmployeeDto employeeDto = new EmployeeDto();
			model.addAttribute("employeeDto", employeeDto);
		} catch (Exception e) {
			System.out.println("showCreateEmployeePage() " + e.getMessage());
		}
		return "employees/create";
	}
	
	@PostMapping( {"/create"} )
	public String createEmployee(@Valid @ModelAttribute EmployeeDto employeeDto, BindingResult result) {
		try {
			if(result.hasErrors()) {
				return "employees/create";
			}
			
			Employee employee = new Employee();
			employee.setCod(employeeDto.getCod());
			employee.setDepartment(employeeDto.getDepartment());
			employee.setLastName(employeeDto.getLastName());
			employee.setName(employeeDto.getName());
			employee.setPosition(employeeDto.getPosition());
			employeeRepository.save(employee);
		} catch (Exception e) {
			System.out.println("processRequest() " + e.getMessage());
		}
		return "redirect:/employees";
	}
	
	@GetMapping( {"/edit"} )
	public String showEditEmployeePage(Model model, @RequestParam int id) {
		try {
			Employee employee = employeeRepository.findById(id).get();
			model.addAttribute("employee", employee);

			EmployeeDto employeeDto = new EmployeeDto();
			employeeDto.setCod(employee.getCod());
			employeeDto.setDepartment(employee.getDepartment());
			employeeDto.setLastName(employee.getLastName());
			employeeDto.setName(employee.getName());
			employeeDto.setPosition(employee.getPosition());
			model.addAttribute("employeeDto", employeeDto);
		} catch (Exception e) {
			System.out.println("showEditEmployeePage() " + e.getMessage());
			return "redirect:/employees";
		}
		return "employees/edit";
	}

	@PostMapping( {"/edit"} )
	public String editEmployee(Model model, @RequestParam int id, @Valid @ModelAttribute EmployeeDto employeeDto, BindingResult result) {
		try {
			Employee employee = employeeRepository.findById(id).get();
			model.addAttribute("employee", employee);
			
			if(result.hasErrors()) {
				return "employees/edit";
			}

			employee.setCod(employeeDto.getCod());
			employee.setDepartment(employeeDto.getDepartment());
			employee.setLastName(employeeDto.getLastName());
			employee.setName(employeeDto.getName());
			employee.setPosition(employeeDto.getPosition());
			employeeRepository.save(employee);
			
		} catch (Exception e) {
			System.out.println("showEditEmployeePage() " + e.getMessage());
		}
		return "redirect:/employees";
	}

	
}
