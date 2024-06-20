package com.projectservice.service;

import com.projectservice.repository.*;
import com.projectservice.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public Project createProject(Project project) {

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




}
