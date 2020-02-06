package com.example.addGlobalPower.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * RecordNotFoundException
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException {

  public RecordNotFoundException(String message) {
    super(message);
  }
}