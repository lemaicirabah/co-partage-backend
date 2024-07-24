package com.evaluationservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "UserService", url = "http://localhost:8080", path = "/co-partage/users")
public interface UserServiceClient {

    @PostMapping("/{userId}/evaluations/givens")
    void addGivenEvaluationToUser(@PathVariable Long userId, @RequestBody Long evaluationId);

    @DeleteMapping("/{userId}/evaluations/givens/{evaluationId}")
    void removeGivenEvaluationToUser(@PathVariable Long userId, @PathVariable Long evaluationId);

    @PostMapping("/{userId}/evaluations/receives")
    void addReceiveEvaluationToUser(@PathVariable Long userId, @RequestBody Long evaluationId);

    @DeleteMapping("/{userId}/evaluations/receives/{evaluationId}")
    void removeReceiveEvaluationToUser(@PathVariable Long userId, @PathVariable Long evaluationId);
}
