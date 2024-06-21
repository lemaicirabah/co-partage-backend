package com.projectservice.service;

public class ProjectException extends RuntimeException{

    private final ErrorResponse errorResponse;

    public ProjectException(ErrorResponse e){
        errorResponse = e;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
