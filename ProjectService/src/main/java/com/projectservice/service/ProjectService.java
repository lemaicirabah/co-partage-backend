package com.projectservice.service;

import com.projectservice.client.EvaluationServiceClient;
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
import java.util.Objects;
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

    @Autowired
    private EvaluationServiceClient evaluationServiceClient;

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

            for (Long id : existingProject.getEvaluations()){
                evaluationServiceClient.deleteEvaluation(id);
            }

            projectRepository.deleteById(projectId);
        }
    }

    // region task ********************************************************************

    @Transactional
    public TaskDto createTask(Long projectId, TaskDto taskDto){

        Project existingProject = projectRepository.findById(projectId).orElse(null);

        if(existingProject != null ){

            if(taskDto.getAssignee() != null && !existingProject.getMembers().contains(taskDto.getAssignee())){
                throw new ProjectException(HttpStatus.BAD_REQUEST, "La personne assignée à la tache doit faire partie des membres du projet");
            }

            Task task = TaskMapper.INSTANCE.taskDtoToTask(taskDto);
            task = taskRepository.save(task);
            existingProject.getTasks().add(task);
            projectRepository.save(existingProject);
            return TaskMapper.INSTANCE.taskToTaskDto(task);

        }
        throw new ProjectException(HttpStatus.NOT_FOUND, "Le projet avec le id : " + projectId + " est introuvable");
    }

    @Transactional
    public TaskDto updateTask(Long projectId, Long taskId, TaskDto taskDto){

        Project existingProject = projectRepository.findById(projectId).orElse(null);
        if(existingProject == null){
            throw new ProjectException(HttpStatus.NOT_FOUND, "Le projet avec le id : " + projectId + " est introuvable");
        }
        Task existingTask = taskRepository.findById(taskId).orElse(null);
        if(existingTask == null ){
            throw new ProjectException(HttpStatus.NOT_FOUND, "La tache avec le id : " + taskId + " est introuvable");
        }

        if(taskDto.getAssignee() != null && !existingProject.getMembers().contains(taskDto.getAssignee())){
            throw new ProjectException(HttpStatus.BAD_REQUEST, "La personne assignée à la tache doit faire partie des membres du projet");
        }

        Task newTask = TaskMapper.INSTANCE.taskDtoToTask(taskDto);
        existingTask.setAssignee(newTask.getAssignee());
        existingTask.setDescription(newTask.getDescription());
        existingTask.setTitle(newTask.getTitle());
        existingTask.setDeadline(newTask.getDeadline());
        existingTask.setStatus(newTask.getStatus());
        existingTask = taskRepository.save(existingTask);
        return TaskMapper.INSTANCE.taskToTaskDto(existingTask);
    }

    public TaskDto getTaskById(Long projectId, Long taskId) {

        Project project = projectRepository.findById(projectId).orElse(null);

        if(project == null){
            throw new ProjectException(HttpStatus.NOT_FOUND, "Le projet avec le id : " + projectId + " est introuvable");
        }
        Task task = taskRepository.findById(taskId).orElse(null);
        if(task == null){
            throw new ProjectException(HttpStatus.NOT_FOUND, "La tache avec le id : " + taskId + " est introuvable");
        }
        return TaskMapper.INSTANCE.taskToTaskDto(task);
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
    public ProjectDto addMember(Long projectId, Long memberId) {

        Project existingProject = projectRepository.findById(projectId).orElse(null);

        if(existingProject != null){
            existingProject.getMembers().add(memberId);
            userServiceClient.addProjectToUser(memberId, existingProject.getId());
            Project p = projectRepository.save(existingProject);
            return ProjectMapper.INSTANCE.projectToProjectDto(p);
        }

        throw new ProjectException(HttpStatus.NOT_FOUND, "Le projet avec le id : " + projectId + " est introuvable");
    }

    @Transactional
    public void deleteMember(Long projectId, Long memberId) {

        Project existingProject = projectRepository.findById(projectId).orElse(null);

        if(existingProject != null){
            existingProject.getMembers().remove(memberId);

            for(Task task : existingProject.getTasks()){
                if(Objects.equals(task.getAssignee() , memberId)){
                    task.setAssignee(null);
                    taskRepository.save(task);
                }
            }
            userServiceClient.removeProjectFromUser(memberId, existingProject.getId());
            projectRepository.save(existingProject);

            //delete le projet si il ny a plus de membre dans le projet
            if(existingProject.getMembers().isEmpty()){
                deleteProject(projectId);
            }
        }
    }

    // region Evaluation ********************************************************************

    @Transactional
    public ProjectDto addEvaluation(Long projectId, Long evaluationId) {

        Project existingProject = projectRepository.findById(projectId).orElse(null);

        if(existingProject != null){
            existingProject.getEvaluations().add(evaluationId);
            Project p = projectRepository.save(existingProject);
            return ProjectMapper.INSTANCE.projectToProjectDto(p);
        }
        throw new ProjectException(HttpStatus.NOT_FOUND, "Le projet avec le id : " + projectId + " est introuvable");
    }

    @Transactional
    public void removeEvaluation(Long projectId, Long evaluationId) {

        Project existingProject = projectRepository.findById(projectId).orElse(null);

        if(existingProject != null){
            existingProject.getEvaluations().remove(evaluationId);
            projectRepository.save(existingProject);
        }
    }


}
