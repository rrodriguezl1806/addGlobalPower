package com.example.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import com.example.exception.user.UserNotFoundException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * CustomGlobalExceptionHandler
 */
@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

  // Let Spring BasicErrorController handle the exception, we just override the status code
  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<CustomErrorResponse> customHandleNotFound(Exception ex, WebRequest request) {

    CustomErrorResponse errors = new CustomErrorResponse();
    errors.setTimestamp(LocalDateTime.now());
    errors.setError(ex.getMessage());
    errors.setStatus(HttpStatus.NOT_FOUND.value());

    return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

}
  
}