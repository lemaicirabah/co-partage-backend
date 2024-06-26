package com.projectservice.service;

import com.projectservice.mapper.ProjectMapper;
import com.projectservice.repository.*;
import com.projectservice.entity.*;

import com.projectservice.dto.ProjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    public List<ProjectDto> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map(ProjectMapper.INSTANCE::projectToProjectDto).collect(Collectors.toList());
    }

    public ProjectDto getProjectById(Long projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        return ProjectMapper.INSTANCE.projectToProjectDto(project);
    }

    @Transactional
    public ProjectDto createProject(ProjectDto projectDto) {

        Project project = ProjectMapper.INSTANCE.projectDtoToProject(projectDto);
        Set<Task> tasks = project.getTasks();
        if (tasks != null) {
            taskRepository.saveAll(tasks);
        }
        Project savedProject = projectRepository.save(project);
        return ProjectMapper.INSTANCE.projectToProjectDto(savedProject);
    }

    @Transactional
    public ProjectDto updateProject(Long projectId, ProjectDto projectDto){

        Project existingProject = projectRepository.findById(projectId).orElse(null);

        if (existingProject != null) {
            Project newProject = ProjectMapper.INSTANCE.projectDtoToProject(projectDto);
            existingProject.setTitle(newProject.getTitle());
            existingProject.setDescription(newProject.getDescription());
            existingProject.setMembers(newProject.getMembers());
            existingProject.setEvaluations(newProject.getEvaluations());
            existingProject.setTasks(newProject.getTasks());

            Set<Task> newTasks = existingProject.getTasks();

            if (newTasks != null) {
                taskRepository.saveAll(newTasks);
            }

            Project updatedProject = projectRepository.save(existingProject);
            return ProjectMapper.INSTANCE.projectToProjectDto(updatedProject);
        }
        return null;
    }

    @Transactional
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllProjects() {
        projectRepository.deleteAll();
    }
}
