package com.example.addGlobalPower.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * CustomGlobalExceptionHandler
 */
@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
    List<String> details = new ArrayList<>();

    if(ex instanceof DataIntegrityViolationException){
      HttpStatus status = HttpStatus.BAD_REQUEST;
      details.add(ex.getCause().getCause().getLocalizedMessage());
      CustomErrorResponse errorMessage = new CustomErrorResponse(new Date(), status,"Duplicate entry", details);
      return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    } else {
      details.add(ex.getMessage());
      HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

      CustomErrorResponse errorMessage = new CustomErrorResponse(new Date(), status,"Server Error", details);
      return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @ExceptionHandler(RecordNotFoundException.class)
  public ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex, WebRequest request) {
    List<String> details = new ArrayList<>();
    details.add(ex.getLocalizedMessage());
    HttpStatus status = HttpStatus.NOT_FOUND;
    CustomErrorResponse errorMessage = new CustomErrorResponse(new Date(), status, ex.getMessage(), details);
    return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus httpStatus, WebRequest request) {

    List<String> details = new ArrayList<>();
    for(ObjectError error : ex.getBindingResult().getAllErrors()) {
        details.add(error.getDefaultMessage());
    }
    HttpStatus status = HttpStatus.BAD_REQUEST;
    CustomErrorResponse errorMessage = new CustomErrorResponse(new Date(), status, "Validation Failed", details);
    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
  }
  
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
    List<String> details = new ArrayList<String>();
    for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
        details.add(violation.getRootBeanClass().getName() + " " + 
          violation.getPropertyPath() + ": " + violation.getMessage());
    }
  
    HttpStatus status = HttpStatus.BAD_REQUEST;
    CustomErrorResponse errorMessage = new CustomErrorResponse(new Date(), status, "Validation Failed", details);
    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
  }

  

}