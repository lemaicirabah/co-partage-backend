package com.userservice.service;

import com.userservice.entity.Skill;
import com.userservice.entity.User;
import com.userservice.repository.SkillRepository;
import com.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SkillRepository skillRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        // Save skills first
        if (user.getProfile() != null && user.getProfile().getSkills() != null) {
            Set<Skill> skills = user.getProfile().getSkills();
            skillRepository.saveAll(skills);
        }
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            user.setProfile(userDetails.getProfile());
            user.setProjects(userDetails.getProjects());
            user.setGroups(userDetails.getGroups());
            user.setNotifications(userDetails.getNotifications());
            user.setEvaluationsGiven(userDetails.getEvaluationsGiven());
            user.setEvaluationsReceived(userDetails.getEvaluationsReceived());
            user.setTags(userDetails.getTags());

            // Save skills first
            if (user.getProfile() != null && user.getProfile().getSkills() != null) {
                Set<Skill> skills = user.getProfile().getSkills();
                skillRepository.saveAll(skills);
            }

            return userRepository.save(user);
        }
        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
