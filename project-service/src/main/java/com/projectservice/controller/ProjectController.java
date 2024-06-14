package com.projectservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/co-partage/projects")
public class ProjectController {

    @GetMapping("/{id}")
    public String getHelloWorld(@PathVariable String id) {
        return "Hello, World! Your ID is " + id;
    }


}
