package com.projectservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "UserService", url = "http://localhost:8080", path = "/co-partage/users")
public interface UserServiceClient {

    @PostMapping("/{userId}/projects")
    void addProjectToUser(@PathVariable Long userId, @RequestBody Long projectId);

    @DeleteMapping("/{userId}/projects/{projectId}")
    void removeProjectFromUser(@PathVariable Long userId, @PathVariable Long projectId);
}
