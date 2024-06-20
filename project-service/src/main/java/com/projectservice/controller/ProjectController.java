package com.projectservice.controller;

import com.projectservice.entity.Project;
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
    public ResponseEntity<?> getUserById(@PathVariable Long id) throws ProjectException {
        Project project = projectService.getProjectById(id);
        return ResponseEntity.ok(project);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createProject( @Valid  @RequestBody Project project) {
        return ResponseEntity.ok(projectService.createProject(project));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@PathVariable Long id, @Valid @RequestBody Project updatedProject) throws ProjectException {
        Project project = projectService.updateProject(id, updatedProject);
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
