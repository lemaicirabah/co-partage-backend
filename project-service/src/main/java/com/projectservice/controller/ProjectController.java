package com.projectservice.controller;

import com.projectservice.entity.Project;
import com.projectservice.entity.Task;
import com.projectservice.service.ProjectException;
import com.projectservice.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public ResponseEntity<?> getProjectById(@PathVariable Long id) throws ProjectException {
        Project project = projectService.getProjectById(id);
        return ResponseEntity.ok(project);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createProject( @Valid  @RequestBody Project project) {
        return ResponseEntity.ok(projectService.createProject(project));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@PathVariable Long id, @RequestBody Map<String, Object> updatedFields ) throws ProjectException {
        Project project = projectService.updateProject(id, updatedFields);
        return ResponseEntity.ok(project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProjectById(@PathVariable Long id) throws ProjectException {
        projectService.deleteProjectById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllProjects()  {
        projectService.deleteAllProjects();
        return ResponseEntity.noContent().build();
    }

    // MEMBERS

    @GetMapping("/{id}/members")
    public ResponseEntity<?> getProjectMembers(@PathVariable Long id) throws ProjectException {
        Set<Long> members = projectService.getMembers(id);
        return ResponseEntity.ok(members);
    }

    @GetMapping("/{projectId}/members/{membersId}")
    public ResponseEntity<?> getProjectMembers(@PathVariable Long projectId ,@PathVariable Long membersId) throws ProjectException {
        Long member = projectService.getMemberById(projectId, membersId);
        return ResponseEntity.ok(member);
    }

    @PostMapping("/{projectId}/members")
    public ResponseEntity<?> addMembers(@PathVariable Long projectId, @RequestBody Set<Long> newMembers) throws ProjectException {
        Set<Long> updatedMembers = projectService.addMembers(projectId, newMembers);
        return ResponseEntity.ok(updatedMembers);
    }

    @DeleteMapping("/{projectId}/members/{memberId}")
    public ResponseEntity<?> deleteMember(@PathVariable Long projectId, @PathVariable Long memberId) throws ProjectException {
        projectService.deleteMemberById(projectId, memberId);
        return ResponseEntity.noContent().build();
    }

    // TASKS

    @GetMapping("/{id}/tasks")
    public ResponseEntity<?> getProjectTasks(@PathVariable Long id) throws ProjectException {
        Set<Task> tasks = projectService.getTasks(id);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{projectId}/tasks/{taskId}")
    public ResponseEntity<?> getProjectTasks(@PathVariable Long projectId, @PathVariable Long taskId) throws ProjectException {
        Task task = projectService.getTaskById(projectId, taskId);
        return ResponseEntity.ok(task);
    }

    @PostMapping("/{projectId}/tasks")
    public ResponseEntity<Task> createTask(@PathVariable Long projectId, @Valid @RequestBody Task task) {
        Task createdTask = projectService.createTask(projectId, task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @DeleteMapping("/{projectId}/tasks/{taskId}")
    public ResponseEntity<?> deleteTaskById(@PathVariable Long projectId, @PathVariable Long taskId) {

        projectService.deleteTaskById(projectId, taskId);
        return  ResponseEntity.noContent().build();
    }

    @PutMapping("/{projectId}/tasks/{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable Long projectId, @PathVariable Long taskId, @RequestBody Map<String, Object> updatedFields ) throws ProjectException {
        Project project = projectService.updateTask(projectId, taskId, updatedFields);
        return ResponseEntity.noContent().build();
    }


    // ExceptionHandlers

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put("messageError", error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    // Gestion des exceptions de type ProjectException
    @ExceptionHandler(ProjectException.class)
    public ResponseEntity<Map<String, String>> handleProjectException(ProjectException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("messageError", ex.getErrorResponse().getMessage());
        return ResponseEntity.status(ex.getErrorResponse().getStatus()).body(errors);
    }

    // Gestion des exceptions pour les autres types d'erreurs non spécifiées
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
        // Enregistrer l'erreur pour le suivi interne (pt pour plus tard)
        //logger.error("Unexpected error occurred", ex);
        Map<String, String> errors = new HashMap<>();
        errors.put("messageError", "An unexpected error occurred. Please try again later.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
    }

}
