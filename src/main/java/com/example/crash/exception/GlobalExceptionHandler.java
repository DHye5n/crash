package com.example.crash.exception;

import com.example.crash.model.error.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     *         4xx 에러
     *
     * */

    @ExceptionHandler(ClientErrorException.class)
    public ResponseEntity<ErrorResponse> handlerClientErrorException(ClientErrorException e) {

        return new ResponseEntity<>(
            new ErrorResponse(e.getStatus(), e.getMessage()), e.getStatus());

    }

    /**
     *         5xx 에러
     *
     * */

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {

        return ResponseEntity.internalServerError().build();

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {

        return ResponseEntity.internalServerError().build();

    }

}
