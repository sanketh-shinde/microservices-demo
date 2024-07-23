package com.eidiko.customer.handler;

import com.eidiko.customer.exception.FoundException;
import com.eidiko.customer.exception.NotFoundException;
import com.eidiko.customer.exception.UnknownException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(UnknownException.class)
    public ResponseEntity<?> handleUnknownException(UnknownException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler(FoundException.class)
    public ResponseEntity<?> handleFoundException(FoundException e) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(e.getMessage());
    }

}