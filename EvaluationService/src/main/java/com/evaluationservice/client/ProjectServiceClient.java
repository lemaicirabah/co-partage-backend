package com.evaluationservice.client;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ProjectService", url = "http://localhost:8080", path = "/co-partage/projects")
public interface ProjectServiceClient {

    @PutMapping("/{projectId}/evaluations")
    public ResponseEntity<?> addEvaluation(@PathVariable Long projectId, @RequestBody Long evaluationId);
}
