package com.example.addGlobalPower.exception;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

public class CustomErrorResponse {

  private Date timestamp;
  private HttpStatus status;
  private String message;
  private List<String> details;

  public CustomErrorResponse() {
  }

  public CustomErrorResponse(Date timestamp,HttpStatus status, String message, List<String> details) {
    this.timestamp = timestamp;
    this.status = status;
    this.message = message;
    this.details = details;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<String> getDetails() {
    return details;
  }

  public void setDetails(List<String> details) {
    this.details = details;
  }
}