package com.projectservice.controller;

import com.projectservice.dto.ProjectDto;
import com.projectservice.dto.TaskDto;
import com.projectservice.exception.ProjectException;
import com.projectservice.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public ResponseEntity<?> getProjectById(@PathVariable Long id) {

        try{
            ProjectDto projectDto = projectService.getProjectById(id);
            return ResponseEntity.ok(projectDto);
        }catch (ProjectException e){
            return ResponseEntity.status(e.getHttpStatus()).body(e.getJsonErrorMessage());
        }
    }

    @PostMapping("/users/{userId}")
    @Operation(summary = "Create a new project", description = "Create a new project")
    public ResponseEntity<ProjectDto> createProject(@PathVariable Long userId, @RequestBody ProjectDto projectDto) {
        ProjectDto createdProject = projectService.createProject(userId, projectDto);
        return ResponseEntity.ok(createdProject);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a project", description = "Update an existing project by ID")
    public ResponseEntity<?> updateProject(@PathVariable Long id, @RequestBody ProjectDto projectDto) {
        try{
            ProjectDto updatedProject = projectService.updateProject(id, projectDto);
            return ResponseEntity.ok(updatedProject);
        }catch(ProjectException e){
            return ResponseEntity.status(e.getHttpStatus()).body(e.getJsonErrorMessage());
        }
    }

    @DeleteMapping("/{projectId}")
    @Operation(summary = "Delete a project", description = "Delete a project by its ID")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }

    // region task ********************************************************************

    @PostMapping("/{projectId}/tasks")
    @Operation(summary = "Create a new Task", description = "Create a new task")
    public ResponseEntity<TaskDto> createTask(@PathVariable Long projectId, @RequestBody TaskDto taskDto) {
        TaskDto createdTask = projectService.createTask(projectId, taskDto);
        if(createdTask != null){
            return ResponseEntity.ok(createdTask);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{projectId}/tasks/{taskId}")
    @Operation(summary = "Get a Task", description = "Get a task by id")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long projectId, @PathVariable Long taskId) {
        TaskDto task = projectService.getTaskById(projectId, taskId);
        if(task != null){
            return ResponseEntity.ok(task);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{projectId}/tasks/{taskId}")
    @Operation(summary = "Delete a Task", description = "Delete a task by id")
    public ResponseEntity<Void> deleteTask(@PathVariable Long projectId, @PathVariable Long taskId) {
        projectService.deleteTask(projectId, taskId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{projectId}/tasks/{taskId}")
    @Operation(summary = "Update a Task", description = "Update a task by id")
    public ResponseEntity<TaskDto> deleteTask(@PathVariable Long projectId, @PathVariable Long taskId, @RequestBody TaskDto taskDto) {
        TaskDto updatedTask = projectService.updateTask(projectId, taskId, taskDto);
        if (updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        }
        return ResponseEntity.notFound().build();
    }

    // region Member ********************************************************************

    @PostMapping("/{projectId}/members/{userId}")
    @Operation(summary = "Add a new member", description = "Add a new member")
    public ResponseEntity<Void> addMember(@PathVariable Long projectId, @PathVariable Long userId) {
        projectService.addMember(projectId, userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{projectId}/members/{userId}")
    @Operation(summary = "Delete a new member", description = "Delete a new member")
    public ResponseEntity<Void> deleteMember(@PathVariable Long projectId, @PathVariable Long userId) {
        projectService.deleteMember(projectId, userId);
        return ResponseEntity.noContent().build();
    }


}