package com.evaluationservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ProjectService", url = "http://localhost:8080", path = "/co-partage/projects")
public interface ProjectServiceClient {

    @PostMapping("/{projectId}/evaluations")
    void addEvaluation(@PathVariable Long projectId, @RequestBody Long evaluationId);

    @DeleteMapping("/{projectId}/evaluations/{evaluationId}")
    void removeEvaluation(@PathVariable Long projectId, @PathVariable Long evaluationId);
}
