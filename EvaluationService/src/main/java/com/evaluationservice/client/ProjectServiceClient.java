package com.evaluationservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ProjectService", url = "http://localhost:8080", path = "/co-partage")
public interface ProjectServiceClient {
}
