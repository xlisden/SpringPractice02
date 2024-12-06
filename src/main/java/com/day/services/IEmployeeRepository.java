package com.day.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.day.beans.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer>{

}
