package com.projectservice.controller;

import com.projectservice.entity.Project;
import com.projectservice.service.ProjectException;
import com.projectservice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Project> getUserById(@PathVariable Long id) {
        Project project = projectService.getProjectById(id);
        if (project != null) {
            return ResponseEntity.ok(project);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project updatedProject) {

        Project project = projectService.updateProject(id, updatedProject);

        if(project != null){
            return ResponseEntity.ok(project);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProjectById(@PathVariable Long id) {

        try {
            projectService.deleteProjectById(id);
            return ResponseEntity.noContent().build();

        } catch (ProjectException e) {
            return ResponseEntity.status(e.getErrorResponse().getStatus()).body(e.getErrorResponse());

        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }

    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllProjects()  {

        try{
            projectService.deleteAllProjects();
            return ResponseEntity.noContent().build();

        }catch(ProjectException e){

            return ResponseEntity.status(e.getErrorResponse().getStatus()).body(e.getErrorResponse());

        }catch (Exception e){

            return ResponseEntity.internalServerError().build();
        }
    }

}
