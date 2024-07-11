package com.projectservice.service;

import com.projectservice.client.UserServiceClient;
import com.projectservice.dto.TaskDto;
import com.projectservice.exception.ProjectException;
import com.projectservice.mapper.ProjectMapper;
import com.projectservice.mapper.TaskMapper;
import com.projectservice.repository.*;
import com.projectservice.entity.*;

import com.projectservice.dto.ProjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserServiceClient userServiceClient;

    public List<ProjectDto> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map(ProjectMapper.INSTANCE::projectToProjectDto).collect(Collectors.toList());
    }

    public ProjectDto getProjectById(Long projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if(project != null){
            return ProjectMapper.INSTANCE.projectToProjectDto(project);
        }
        throw new ProjectException(HttpStatus.NOT_FOUND, "Le projet avec le id : " + projectId + " est introuvable");
    }

    @Transactional
    public ProjectDto createProject(Long userId, ProjectDto projectDto) {

        Project project = ProjectMapper.INSTANCE.projectDtoToProject(projectDto);

        project.setCreator(userId);
        Set<Long> members = new HashSet<Long>();
        members.add(userId);
        project.setMembers(members);
        project.setTasks(null);
        project.setEvaluations(null);

        Project savedProject = projectRepository.save(project);
        userServiceClient.addProjectToUser(userId, savedProject.getId());

        return ProjectMapper.INSTANCE.projectToProjectDto(savedProject);
    }

    @Transactional
    public ProjectDto updateProject(Long projectId, ProjectDto projectDto){

        Project existingProject = projectRepository.findById(projectId).orElse(null);

        if (existingProject != null) {
            Project newProject = ProjectMapper.INSTANCE.projectDtoToProject(projectDto);
            existingProject.setTitle(newProject.getTitle());
            existingProject.setDescription(newProject.getDescription());

            Project updatedProject = projectRepository.save(existingProject);
            return ProjectMapper.INSTANCE.projectToProjectDto(updatedProject);
        }
        throw new ProjectException(HttpStatus.NOT_FOUND, "Le projet avec le id : " + projectId + " est introuvable");
    }

    @Transactional
    public void deleteProject(Long projectId) {

        Project existingProject = projectRepository.findById(projectId).orElse(null);

        if(existingProject != null){

            userServiceClient.removeProjectFromUser(existingProject.getCreator(), projectId);

            for (Long id : existingProject.getMembers()){
                userServiceClient.removeProjectFromUser(id, existingProject.getId());
            }

            projectRepository.deleteById(projectId);
        }
    }

    // region task ********************************************************************

    @Transactional
    public TaskDto createTask(Long projectId, TaskDto taskDto){

        Project existingProject = projectRepository.findById(projectId).orElse(null);

        if(taskDto.getAssignee() != null){
            if(existingProject != null && existingProject.getMembers().contains(taskDto.getAssignee())){
                Task task = TaskMapper.INSTANCE.taskDtoToTask(taskDto);
                task = taskRepository.save(task);
                existingProject.getTasks().add(task);
                projectRepository.save(existingProject);
                return TaskMapper.INSTANCE.taskToTaskDto(task);
            }
        }

        return null;
    }

    @Transactional
    public TaskDto updateTask(Long projectId, Long taskId, TaskDto taskDto){

        Project existingProject = projectRepository.findById(projectId).orElse(null);
        Task existingTask = taskRepository.findById(taskId).orElse(null);

        if (existingProject != null && existingTask != null && existingProject.getMembers().contains(taskDto.getAssignee())) {

            Task newTask = TaskMapper.INSTANCE.taskDtoToTask(taskDto);
            existingTask.setAssignee(newTask.getAssignee());
            existingTask.setDescription(newTask.getDescription());
            existingTask.setTitle(newTask.getTitle());
            existingTask.setDeadline(newTask.getDeadline());
            existingTask.setStatus(newTask.getStatus());
            existingTask = taskRepository.save(existingTask);
            return TaskMapper.INSTANCE.taskToTaskDto(existingTask);
        }
        return null;
    }

    public TaskDto getTaskById(Long projectId, Long taskId) {

        Project project = projectRepository.findById(projectId).orElse(null);

        if(project != null){
            Task task = taskRepository.findById(taskId).orElse(null);
            return TaskMapper.INSTANCE.taskToTaskDto(task);
        }

        return null;
    }

    @Transactional
    public void deleteTask(Long projectId, Long taskId) {

        Project existingProject = projectRepository.findById(projectId).orElse(null);
        if(existingProject != null){
            Task existingTask = taskRepository.findById(taskId).orElse(null);
            existingProject.getTasks().remove(existingTask);
            projectRepository.save(existingProject);
            taskRepository.deleteById(taskId);
        }
    }

    // region members ********************************************************************

    @Transactional
    public void addMember(Long projectId, Long memberId) {

        Project existingProject = projectRepository.findById(projectId).orElse(null);

        if(existingProject != null){
            existingProject.getMembers().add(memberId);
            userServiceClient.addProjectToUser(memberId, existingProject.getId());
            projectRepository.save(existingProject);
        }
    }

    @Transactional
    public void deleteMember(Long projectId, Long memberId) {

        Project existingProject = projectRepository.findById(projectId).orElse(null);

        if(existingProject != null){
            existingProject.getMembers().remove(memberId);
            userServiceClient.removeProjectFromUser(memberId, existingProject.getId());
            projectRepository.save(existingProject);
        }
    }

}
