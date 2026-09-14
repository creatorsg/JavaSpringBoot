package com.rookies6.myspringboot4project.controller;

import com.rookies6.myspringboot4project.exception.DuplicateStudentException;
import com.rookies6.myspringboot4project.exception.ErrorResponse;
import com.rookies6.myspringboot4project.exception.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateStudentException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(DuplicateStudentException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT) // 409
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(StudentNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND) // 404
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST) // 400
                .body(new ErrorResponse(message));
    }
}