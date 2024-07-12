package com.userservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ProjectService", url = "http://localhost:8080", path = "/co-partage")
public interface ProjectServiceClient {

    @DeleteMapping("/projects/{projectId}/members/{userId}")
    ResponseEntity<Void> deleteMember(@PathVariable Long projectId, @PathVariable Long userId);
}
