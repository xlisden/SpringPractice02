package com.day.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.day.beans.Assignment;

public interface IAssignmentRepository extends JpaRepository<Assignment, Integer> {

	@Query(value = "SELECT fnIsEmployeeAtProject(:id_project, :id_employee)", nativeQuery = true)
	public boolean fnIsEmployeeAsignment(@Param("id_project") Integer idProject, @Param("id_employee") Integer idEmployee);
	
	@Query(value = "SELECT fnGetIdAssignment(:id_project, :id_employee)", nativeQuery = true)
	public int fnGetIdAsignment(@Param("id_project") Integer idProject, @Param("id_employee") Integer idEmployee);
}
