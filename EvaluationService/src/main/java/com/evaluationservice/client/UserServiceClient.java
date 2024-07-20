package com.evaluationservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "UserService", url = "http://localhost:8080", path = "/co-partage/users")
public interface UserServiceClient {

    @PutMapping("/{userId}/evaluations/givens")
    void addGivenEvaluationToUser(@PathVariable Long userId, @RequestBody Long evaluationId);

    @PutMapping("/{userId}/evaluations/receives")
    void addReceiveEvaluationToUser(@PathVariable Long userId, @RequestBody Long evaluationId);
}
