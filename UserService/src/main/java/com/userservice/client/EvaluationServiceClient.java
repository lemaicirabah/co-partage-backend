package com.userservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "EvaluationService", url = "http://localhost:8085", path = "/co-partage/evaluations")
public interface EvaluationServiceClient {

    @DeleteMapping("/{id}")
    void deleteEvaluation(@PathVariable Long id);
}
