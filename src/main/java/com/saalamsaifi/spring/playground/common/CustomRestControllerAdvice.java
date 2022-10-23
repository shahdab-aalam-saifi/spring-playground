package com.saalamsaifi.spring.playground.common;

import com.saalamsaifi.spring.playground.common.exception.NoPersonFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomRestControllerAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {NoPersonFoundException.class})
  public ResponseEntity<NoPersonFoundException> handleNoPersonFoundException(
    NoPersonFoundException exception, WebRequest request) {
    return ResponseEntity.badRequest().body(exception);
  }
}
