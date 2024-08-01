package com.userservice.controller;

import com.userservice.dto.UserDto;
import com.userservice.exception.UserException;
import com.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/co-partage/users")
@Tag(name = "User", description = "User management APIs")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieve a list of all users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieve a user by its ID")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            UserDto userDto = userService.getUserById(id);
            return ResponseEntity.ok(userDto);
        } catch (UserException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getJsonErrorMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to create user", e);
        }
    }

    @PostMapping
    @Operation(summary = "Create a new user", description = "Create a new user")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        try {
            UserDto createdUser = userService.createUser(userDto);
            return ResponseEntity.ok(createdUser);
        } catch (UserException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getJsonErrorMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to create user", e);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a user", description = "Update an existing user by ID")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        try {
            UserDto updatedUser = userService.updateUser(id, userDto);
            return ResponseEntity.ok(updatedUser);
        } catch (UserException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getJsonErrorMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to create user", e);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user", description = "Delete a user by its ID")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to delete user", e);
        }
    }

    // Section login *************************************************

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        try {
            String username = loginRequest.get("username");
            System.out.println("Attempting to log in user: " + username); // Logging statement
            UserDto userDto = userService.login(username);
            return ResponseEntity.ok(userDto);
        } catch (UserException e) {
            System.err.println("UserException occurred: " + e.getMessage()); // Logging statement
            return ResponseEntity.status(e.getHttpStatus()).body(e.getJsonErrorMessage());
        }
    }

    // Section project *************************************************

    @PostMapping("/{userId}/projects")
    @Operation(summary = "add a project", description = "Add an existing project by ID")
    public ResponseEntity<Void> addProjectToUser(@PathVariable Long userId, @RequestBody Long projectId) {
        try {
            userService.addProjectToUser(userId, projectId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to update project id", e);
        }
    }

    @DeleteMapping("/{userId}/projects/{projectId}")
    @Operation(summary = "remove a project", description = "Remove an existing project by ID")
    public ResponseEntity<Void> removeProjectFromUser(@PathVariable Long userId, @PathVariable Long projectId) {
        try {
            userService.removeProjectFromUser(userId, projectId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to delete project", e);
        }
    }

    // Section evaluation *************************************************

    @PostMapping("/{userId}/evaluations/givens")
    @Operation(summary = "add a given evaluation", description = "add a given evaluation by ID")
    public ResponseEntity<Void> addGivenEvaluationToUser(@PathVariable Long userId, @RequestBody Long evaluationId) {
        try {
            userService.addGivenEvaluationToUser(userId, evaluationId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to add evaluation id", e);
        }
    }

    @DeleteMapping("/{userId}/evaluations/givens/{evaluationId}")
    @Operation(summary = "remove a given evaluation", description = "remove a given evaluation by ID")
    public ResponseEntity<Void> removeGivenEvaluationToUser(@PathVariable Long userId, @PathVariable Long evaluationId) {
        try {
            userService.removeGivenEvaluationToUser(userId, evaluationId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to add evaluation id", e);
        }
    }

    @PostMapping("/{userId}/evaluations/receives")
    @Operation(summary = "add a receive evaluation", description = "add a receive evaluation by ID")
    public ResponseEntity<Void> addReceiveEvaluationToUser(@PathVariable Long userId, @RequestBody Long evaluationId) {
        try {
            userService.addReceiveEvaluationToUser(userId, evaluationId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to remove evaluation id", e);
        }
    }

    @DeleteMapping("/{userId}/evaluations/receives/{evaluationId}")
    @Operation(summary = "remove a receive evaluation", description = "remove a receive evaluation by ID")
    public ResponseEntity<Void> removeReceiveEvaluationToUser(@PathVariable Long userId, @PathVariable Long evaluationId) {
        try {
            userService.removeReceiveEvaluationToUser(userId, evaluationId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to add evaluation id", e);
        }
    }


}
