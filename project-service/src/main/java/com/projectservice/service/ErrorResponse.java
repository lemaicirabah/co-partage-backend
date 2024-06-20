package com.projectservice.service;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    private String message;
    private final HttpStatus status;

    public ErrorResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    // Getters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

}

