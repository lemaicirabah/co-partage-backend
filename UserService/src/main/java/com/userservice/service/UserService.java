package com.userservice.service;

import com.userservice.client.ProjectServiceClient;
import com.userservice.dto.UserDto;
import com.userservice.entity.Skill;
import com.userservice.entity.User;
import com.userservice.exception.UserException;
import com.userservice.mapper.UserMapper;
import com.userservice.repository.SkillRepository;
import com.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
        return user != null ? UserMapper.INSTANCE.userToUserDto(user) : null;
    }


    public UserDto createUser(UserDto userDto) {

        if (userDto.getUsername() == null || userDto.getUsername().isEmpty()){
            throw  new UserException(HttpStatus.BAD_REQUEST, "Le username est un champ obligatoire !");
        }

        Optional<User> existingUser = userRepository.findByUsername(userDto.getUsername());
        if (existingUser.isPresent()) {
            throw  new UserException(HttpStatus.BAD_REQUEST, "Le username " + userDto.getUsername() + " existe déjà !");
        }

        if(userDto.getProjects() != null || userDto.getEvaluationsGiven() != null || userDto.getEvaluationsReceived() != null){
            throw  new UserException(HttpStatus.BAD_REQUEST, "Seuls les champs spécifiques à l'utilisateur sont autorisés !");
        }

        User user = UserMapper.INSTANCE.userDtoToUser(userDto);
        return getUserDto(user);
    }

    public UserDto updateUser(Long id, UserDto userDto) {

        User existingUser = userRepository.findById(id).orElse(null);

        if (userDto.getUsername() == null || userDto.getUsername().isEmpty()){
            throw  new UserException(HttpStatus.BAD_REQUEST, "Le username est un champ obligatoire !");
        }

        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw  new UserException(HttpStatus.BAD_REQUEST, "Le username " + userDto.getUsername() + " existe déjà !");
        }

        if (existingUser != null) {

            existingUser.setUsername(userDto.getUsername());
            existingUser.setEmail(userDto.getEmail());
            existingUser.setPassword(userDto.getPassword());
            existingUser.setProfile(UserMapper.INSTANCE.userDtoToUser(userDto).getProfile());

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

        User existingUser = userRepository.findById(id).orElse(null);

        if(existingUser != null){
            for(Long projectId : existingUser.getProjects()){
                projectServiceClient.deleteMember(projectId, id);

            }
            userRepository.deleteById(id);
        }
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

// Section login *************************************************

    public UserDto login(String username) {

        Optional<User> user = userRepository.findByUsername(username);

        if(user.isPresent()){
            return UserMapper.INSTANCE.userToUserDto(user.get());
        }

        throw  new UserException(HttpStatus.BAD_REQUEST, "Le username " + username + " n'existe pas !");
    }

// Section project *************************************************

    public void addProjectToUser(Long userId, Long projectId){

        User user = userRepository.findById(userId).orElse(null);

        if(user != null){
            user.getProjects().add(projectId);
            userRepository.save(user);
        }
    }

    public void removeProjectFromUser(Long userId, Long projectId){

        User user = userRepository.findById(userId).orElse(null);

        if(user != null){
            user.getProjects().remove(projectId);
            userRepository.save(user);
        }
    }

    // Section evaluation *************************************************

    public void addGivenEvaluationToUser(Long userId, Long evaluationId){

        User user = userRepository.findById(userId).orElse(null);

        if(user != null){
            user.getEvaluationsGiven().add(evaluationId);
            userRepository.save(user);
        }
    }

    public void addReceiveEvaluationToUser(Long userId, Long evaluationId){

        User user = userRepository.findById(userId).orElse(null);

        if(user != null){
            user.getEvaluationsReceived().add(evaluationId);
            userRepository.save(user);
        }
    }

}