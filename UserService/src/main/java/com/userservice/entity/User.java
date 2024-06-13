package com.userservice.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private Profile profile;

    @ManyToMany
    private Set<Project> projects;

    @ManyToMany
    private Set<UserGroup> groups;

    @OneToMany(mappedBy = "recipient")
    private Set<Notification> notifications;

    @OneToMany(mappedBy = "evaluator")
    private Set<Evaluation> evaluationsGiven;

    @OneToMany(mappedBy = "evaluatee")
    private Set<Evaluation> evaluationsReceived;

    @ManyToMany
    private Set<Tag> tags;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Set<UserGroup> getGroups() {
        return groups;
    }

    public void setGroups(Set<UserGroup> groups) {
        this.groups = groups;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    public Set<Evaluation> getEvaluationsReceived() {
        return evaluationsReceived;
    }

    public void setEvaluationsReceived(Set<Evaluation> evaluationsReceived) {
        this.evaluationsReceived = evaluationsReceived;
    }

    public Set<Evaluation> getEvaluationsGiven() {
        return evaluationsGiven;
    }

    public void setEvaluationsGiven(Set<Evaluation> evaluationsGiven) {
        this.evaluationsGiven = evaluationsGiven;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
