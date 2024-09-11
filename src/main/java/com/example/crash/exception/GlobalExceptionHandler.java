package com.example.crash.exception;

import com.example.crash.model.error.ClientErrorResponse;
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
    public ResponseEntity<ClientErrorResponse> handlerClientErrorException(ClientErrorException e) {

        return new ResponseEntity<>(
            new ClientErrorResponse(e.getStatus(), e.getMessage()), e.getStatus());

    }

    /**
     *         5xx 에러
     *
     * */

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ClientErrorResponse> handleRuntimeException(RuntimeException e) {

        return ResponseEntity.internalServerError().build();

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ClientErrorResponse> handleException(Exception e) {

        return ResponseEntity.internalServerError().build();

    }

}
