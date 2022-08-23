package com.example.backend.todolist.controllerAdvice;

import com.example.backend.todolist.dto.errorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice("com.example.backend.todolist.controller")
public class ExceptionAdvisor {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<errorResponse> returnErrorResponse(RuntimeException exception){
        errorResponse errorResponse = new errorResponse(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
