package com.userservice.service;

import com.userservice.client.ProjectServiceClient;
import com.userservice.dto.ProjectDto;
import com.userservice.dto.UserDto;
import com.userservice.entity.Skill;
import com.userservice.entity.User;
import com.userservice.mapper.UserMapper;
import com.userservice.repository.SkillRepository;
import com.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private ProjectServiceClient projectServiceClient;

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper.INSTANCE::userToUserDto).collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {

        User user = userRepository.findById(id).orElse(null);

        if (user != null) {

            UserDto userDto = UserMapper.INSTANCE.userToUserDto(user);

            Set<ProjectDto> projects = getProjectDetails(user.getProjects());
            userDto.setProjectsDetails(projects);

            return userDto;
        }
        return null;
    }

    private Set<ProjectDto> getProjectDetails(Set<Long> projectIds) {
        Set<ProjectDto> projects = new HashSet<>();
        if (projectIds != null && !projectIds.isEmpty()) {
            for (Long projectId : projectIds) {
                ProjectDto projectDto = projectServiceClient.getProjectById(projectId);
                if (projectDto != null) {
                    projects.add(projectDto);
                }
            }
        }
        return projects;
    }

    public UserDto createUser(UserDto userDto) {
        userDto.setProjects(null);
        userDto.setProjectsDetails(null);
        User user = UserMapper.INSTANCE.userDtoToUser(userDto);
        return getUserDto(user);
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(userDto.getUsername());
            existingUser.setEmail(userDto.getEmail());
            existingUser.setPassword(userDto.getPassword());
            existingUser.setProfile(UserMapper.INSTANCE.userDtoToUser(userDto).getProfile());
            existingUser.setTags(UserMapper.INSTANCE.userDtoToUser(userDto).getTags());

            return getUserDto(existingUser);
        }
        return null;
    }

    private UserDto getUserDto(User existingUser) {
        if (existingUser.getProfile() != null && existingUser.getProfile().getSkills() != null) {
            Set<Skill> skills = existingUser.getProfile().getSkills();
            skillRepository.saveAll(skills);
        }

        User updatedUser = userRepository.save(existingUser);
        return UserMapper.INSTANCE.userToUserDto(updatedUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

// Section project *************************************************

    public ProjectDto createProject(ProjectDto projectDto, Long id){

        User user = userRepository.findById(id).orElse(null);

        if(user != null){

            projectDto.setCreator(user.getId());
            ProjectDto createdProject = projectServiceClient.createProject(projectDto);
            user.getProjects().add(createdProject.getId());

            userRepository.save(user);
            return createdProject;
        }
        return null;
    }

    public void deleteProject(Long userId, Long projectId){

        User user = userRepository.findById(userId).orElse(null);

        if(user != null){
            if(user.getProjects() != null && user.getProjects().contains(projectId)){

                projectServiceClient.deleteProject(projectId);
                user.getProjects().remove(projectId);
                userRepository.save(user);
            }
        }
    }

}