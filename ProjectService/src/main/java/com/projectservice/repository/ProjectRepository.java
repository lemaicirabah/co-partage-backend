package com.projectservice.repository;

import com.projectservice.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProjectRepository extends JpaRepository<Project, Long> {
}

