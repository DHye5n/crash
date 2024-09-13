package com.example.crash.exception;

import com.example.crash.model.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMessage = e.getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList())
                .toString();
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.BAD_REQUEST, errorMessage), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handlerHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.BAD_REQUEST, "Required request body is missing."), HttpStatus.BAD_REQUEST);
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
