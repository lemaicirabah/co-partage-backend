package com.projectservice.controller;

import com.projectservice.entity.Project;
import com.projectservice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/co-partage/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;


    @GetMapping
    public List<Project> getAllUsers() {
        return projectService.getAllProjects();
    }


    @GetMapping("/{id}")
    public String getHelloWorld(@PathVariable String id) {
        return "Hello, World! Your ID is " + id;
    }


}
