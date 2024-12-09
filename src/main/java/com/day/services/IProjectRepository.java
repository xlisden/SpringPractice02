package com.day.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.day.beans.Project;

public interface IProjectRepository extends JpaRepository<Project, Integer>{

}
