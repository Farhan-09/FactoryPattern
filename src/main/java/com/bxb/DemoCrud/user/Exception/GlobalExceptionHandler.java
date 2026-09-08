package com.bxb.DemoCrud.user.Exception;



import jakarta.servlet.http.HttpServletRequest;


import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // =========================================================
    // 1. VALIDATION ERROR - @Valid
    // =========================================================

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validationException(
            MethodArgumentNotValidException exception,
            HttpServletRequest request) {

        Map<String, String> errors = new LinkedHashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        log.warn(
                "Validation failed | method={} | path={} | errors={}",
                request.getMethod(),
                request.getRequestURI(),
                errors
        );

        ErrorResponse response = ErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message("Validation failed")
                .path(request.getRequestURI())
                .errors(errors)
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }


    // =========================================================
    // 2. DUPLICATE EMAIL
    // =========================================================

    @ExceptionHandler(DulplicateEmailException.class)
    public ResponseEntity<ErrorResponse> duplicateEmail(
            DulplicateEmailException exception,
            HttpServletRequest request) {

        log.warn(
                "Duplicate email | method={} | path={} | message={}",
                request.getMethod(),
                request.getRequestURI(),
                exception.getMessage()
        );

        ErrorResponse response = ErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .statusCode(HttpStatus.CONFLICT.value())
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }


    // =========================================================
    // 3. USER NOT FOUND / RUNTIME EXCEPTION
    // =========================================================

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> runtimeException(
            RuntimeException exception,
            HttpServletRequest request) {

        log.warn(
                "Runtime exception | method={} | path={} | message={}",
                request.getMethod(),
                request.getRequestURI(),
                exception.getMessage()
        );

        ErrorResponse response = ErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }


    // =========================================================
    // 4. INVALID JSON
    // =========================================================

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> invalidJson(
            HttpMessageNotReadableException exception,
            HttpServletRequest request) {

        log.warn(
                "Invalid request body | method={} | path={}",
                request.getMethod(),
                request.getRequestURI()
        );

        ErrorResponse response = ErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message("Invalid request body. Please check your JSON format.")
                .path(request.getRequestURI())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }


    // =========================================================
    // 5. CONSTRAINT VALIDATION ERROR
    // =========================================================




    // =========================================================
    // 6. WRONG HTTP METHOD
    // =========================================================

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> methodNotAllowed(
            HttpRequestMethodNotSupportedException exception,
            HttpServletRequest request) {

        log.warn(
                "HTTP method not supported | method={} | path={}",
                request.getMethod(),
                request.getRequestURI()
        );

        ErrorResponse response = ErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .statusCode(HttpStatus.METHOD_NOT_ALLOWED.value())
                .message("HTTP method " + request.getMethod()
                        + " is not allowed for this endpoint.")
                .path(request.getRequestURI())
                .build();

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(response);
    }


    // =========================================================
    // 7. UNSUPPORTED CONTENT TYPE
    // =========================================================

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponse> unsupportedMediaType(
            HttpMediaTypeNotSupportedException exception,
            HttpServletRequest request) {

        log.warn(
                "Unsupported content type | method={} | path={}",
                request.getMethod(),
                request.getRequestURI()
        );

        ErrorResponse response = ErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .statusCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .message("Unsupported content type. Please send JSON.")
                .path(request.getRequestURI())
                .build();

        return ResponseEntity
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(response);
    }


    // =========================================================
    // 8. ALL OTHER UNEXPECTED ERRORS
    // =========================================================

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> internalException(
            Exception exception,
            HttpServletRequest request) {

        log.error(
                "Unexpected server error | method={} | path={}",
                request.getMethod(),
                request.getRequestURI(),
                exception
        );

        ErrorResponse response = ErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Something went wrong. Please try again later.")
                .path(request.getRequestURI())
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFound(
            UserNotFoundException exception,
            HttpServletRequest request) {

        log.warn(
                "User not found | method={} | path={} | message={}",
                request.getMethod(),
                request.getRequestURI(),
                exception.getMessage()
        );

        ErrorResponse response = ErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }
}