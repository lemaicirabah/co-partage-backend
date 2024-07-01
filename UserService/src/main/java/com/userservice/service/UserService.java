package com.userservice.service;

import com.userservice.dto.UserDto;
import com.userservice.entity.Skill;
import com.userservice.entity.User;
import com.userservice.mapper.UserMapper;
import com.userservice.repository.SkillRepository;
import com.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SkillRepository skillRepository;

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper.INSTANCE::userToUserDto).collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user != null ? UserMapper.INSTANCE.userToUserDto(user) : null;
    }

    public UserDto createUser(UserDto userDto) {
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
            existingUser.setProjects(UserMapper.INSTANCE.userDtoToUser(userDto).getProjects());
            existingUser.setGroups(UserMapper.INSTANCE.userDtoToUser(userDto).getGroups());
            existingUser.setNotifications(UserMapper.INSTANCE.userDtoToUser(userDto).getNotifications());
            existingUser.setEvaluationsGiven(UserMapper.INSTANCE.userDtoToUser(userDto).getEvaluationsGiven());
            existingUser.setEvaluationsReceived(UserMapper.INSTANCE.userDtoToUser(userDto).getEvaluationsReceived());
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
}