package com.userservice.exception;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class UserException extends RuntimeException{

    private final HttpStatus status;
    private final String errorMessage;


    public UserException(HttpStatus status , String errorMessage) {
        super(errorMessage);
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public HttpStatus getHttpStatus( ) {
        return status;
    }

    public Map<String, String> getJsonErrorMessage(){
        Map<String, String> map = new HashMap<>();
        map.put("ErrorMessage", errorMessage);
        return map;
    }
}
