package com.projectservice.service;

import com.projectservice.repository.*;
import com.projectservice.entity.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.lang.reflect.Field;



@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long projectId) throws ProjectException {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectException(
                        new ErrorResponse("Project with id " + projectId + " does not exist" , HttpStatus.NOT_FOUND)));
    }

    public Project createProject(@NotNull Project project) {

        // Vérifiez si des tâches sont présentes dans le projet
        Set<Task> tasks = project.getTasks();

        if (tasks != null) {
            // Sauvegarder chaque tâche individuellement
            for (Task task : tasks) {
                taskRepository.save(task);
            }
        }

        // Sauvegarder le projet avec les tâches associées
        return projectRepository.save(project);
    }

    // Pour remplacer une ressource entière
    public Project updateProject(Long projectId, Project updatedProject) throws ProjectException {

        Project existingProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectException(
                        new ErrorResponse("Project with id " + projectId + " does not exist" , HttpStatus.NOT_FOUND)));


        // Mettre à jour les propriétés du projet existant
        existingProject.setTitle(updatedProject.getTitle());
        existingProject.setDescription(updatedProject.getDescription());
        existingProject.setCreator(updatedProject.getCreator());
        existingProject.setMembers(updatedProject.getMembers());


        existingProject.setEvaluations(updatedProject.getEvaluations());
        existingProject.setTasks(updatedProject.getTasks());

        // Sauvegarder le projet mis à jour
        return projectRepository.save(existingProject);

    }

    @Transactional
    public void deleteProjectById(Long projectId) throws ProjectException {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectException(
                        new ErrorResponse("Project with id " + projectId + " does not exist" , HttpStatus.NOT_FOUND)));

        // Supprimer le projet
        projectRepository.delete(project);
    }

    @Transactional
    public void deleteAllProjects(){
        projectRepository.deleteAll();
    }

}
