package com.bxb.DemoCrud.user.Exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> internalException(
            Exception exception,
            HttpServletRequest httpServletRequest) {

        exception.printStackTrace();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Something Went Wrong, Please Try Again Later: "
                        + exception.getMessage())
                .path(httpServletRequest.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }


    @ExceptionHandler(DulplicateEmailException.class)
    public ResponseEntity<ErrorResponse> duplicateEmail(
            DulplicateEmailException exception,
            HttpServletRequest httpServletRequest) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .statusCode(HttpStatus.CONFLICT.value())
                .message(exception.getMessage())
                .path(httpServletRequest.getRequestURI())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(errorResponse);
    }
}