package com.userservice.client;

import com.userservice.dto.ProjectDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ProjectService", url = "http://localhost:8080", path = "/co-partage")
public interface ProjectServiceClient {

    @GetMapping("/projects/{id}")
    ProjectDto getProjectById(@PathVariable("id") Long id);

    @PostMapping("/projects")
    ProjectDto createProject(@RequestBody ProjectDto projectDto);

    @DeleteMapping("/projects/{id}")
    void deleteProject(@PathVariable Long id);
}
