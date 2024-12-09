package com.day.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.day.beans.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer>{
//public interface IEmployeeRepository extends CrudRepository<Employee, Integer>{
	
	@Query(value = "CALL `bdspring02`.`spReadEmployeesPerProject`(:id_project)", nativeQuery = true)
	List<Employee> spReadEmployeesPerProject(@Param("id_project") Integer idProject);

}
