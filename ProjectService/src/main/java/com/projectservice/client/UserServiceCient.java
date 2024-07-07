package com.projectservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "UserService", url = "http://localhost:8080", path = "/co-partage")
public interface UserServiceCient {
}
