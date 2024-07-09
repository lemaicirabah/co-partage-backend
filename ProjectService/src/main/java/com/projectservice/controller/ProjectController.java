package com.projectservice.controller;

import com.projectservice.dto.ProjectDto;
import com.projectservice.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/co-partage/projects")
@Tag(name = "Project", description = "Project management APIs")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    @Operation(summary = "Get all projects", description = "Retrieve a list of all projects")
    public List<ProjectDto> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get project by ID", description = "Retrieve a project by its ID")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long id) {
        ProjectDto projectDto = projectService.getProjectById(id);
        if (projectDto != null) {
            return ResponseEntity.ok(projectDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{userId}")
    @Operation(summary = "Create a new project", description = "Create a new project")
    public ResponseEntity<ProjectDto> createProject(@PathVariable Long userId, @Valid @RequestBody ProjectDto projectDto) {
        ProjectDto createdProject = projectService.createProject(userId, projectDto);
        return ResponseEntity.ok(createdProject);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a project", description = "Update an existing project by ID")
    public ResponseEntity<ProjectDto> updateProject(@PathVariable Long id, @Valid @RequestBody ProjectDto projectDto) {
        ProjectDto updatedProject = projectService.updateProject(id, projectDto);
        if (updatedProject != null) {
            return ResponseEntity.ok(updatedProject);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{projectId}")
    @Operation(summary = "Delete a project", description = "Delete a project by its ID")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }
}