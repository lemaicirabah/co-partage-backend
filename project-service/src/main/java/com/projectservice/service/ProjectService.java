package com.projectservice.service;

import com.projectservice.repository.*;
import com.projectservice.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getUserById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

//    public Project createProject(Project project) {
//        // Save skills first
//        if (user.getProfile() != null && user.getProfile().getSkills() != null) {
//            Set<Skill> skills = user.getProfile().getSkills();
//            skillRepository.saveAll(skills);
//        }
//        return userRepository.save(user);
//    }



}
