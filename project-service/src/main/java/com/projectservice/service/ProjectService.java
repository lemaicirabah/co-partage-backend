package com.projectservice.service;

import com.projectservice.repository.*;
import com.projectservice.entity.*;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.lang.reflect.Field;
import org.springframework.util.ReflectionUtils;



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

    @Transactional
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
    @Transactional
    public Project updateProject(Long projectId, Map<String, Object> updatedFields) throws ProjectException {

        Project existingProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectException(
                        new ErrorResponse("Project with id " + projectId + " does not exist" , HttpStatus.NOT_FOUND)));


        // Mettre à jour les propriétés du projet existant seulement pour dscription et title de projet !!!
        updatedFields.forEach((key, value) -> {
            if (!key.equals("title") && !key.equals("description")) {
                throw new ProjectException(
                        new ErrorResponse("Field " + key + " is not allowed to be updated", HttpStatus.BAD_REQUEST));
            }
            Field field = ReflectionUtils.findField(Project.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingProject, value);
            }
        });

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

    // MEMBERS

    public Set<Long> getMembers(Long projectId) throws ProjectException {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectException(
                        new ErrorResponse("Project with id " + projectId + " does not exist" , HttpStatus.NOT_FOUND)));

        return project.getMembers();
    }

    public Long getMemberById(Long projectId, Long memberId) throws ProjectException {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectException(
                        new ErrorResponse("Project with id " + projectId + " does not exist" , HttpStatus.NOT_FOUND)));

        // Vérifier si le membre existe dans le Set de membres
        if (project.getMembers().contains(memberId)) {
            return memberId;
        } else {
            throw new ProjectException(
                    new ErrorResponse("Member with id " + memberId + " does not exist in project " + projectId, HttpStatus.NOT_FOUND));
        }
    }

    @Transactional
    public Set<Long> addMembers(Long projectId, Set<Long> newMembers) throws ProjectException {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectException(
                        new ErrorResponse("Project with id " + projectId + " does not exist", HttpStatus.NOT_FOUND)));

        project.getMembers().addAll(newMembers);
        projectRepository.save(project);
        return project.getMembers();
    }

    @Transactional
    public void deleteMemberById(Long projectId, Long memberId) throws ProjectException {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectException(
                        new ErrorResponse("Project with id " + projectId + " does not exist", HttpStatus.NOT_FOUND)));

        if (project.getMembers().contains(memberId)) {
            project.getMembers().remove(memberId);
            projectRepository.save(project);
        } else {
            throw new ProjectException(
                    new ErrorResponse("Member with id " + memberId + " does not exist in project " + projectId, HttpStatus.NOT_FOUND));
        }
    }


}
