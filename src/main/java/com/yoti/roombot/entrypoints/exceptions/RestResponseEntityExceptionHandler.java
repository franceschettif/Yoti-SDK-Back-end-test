package com.yoti.roombot.entrypoints.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
    extends ResponseEntityExceptionHandler {

  @ExceptionHandler( {IllegalArgumentException.class,
                      IllegalStateException.class,
                      InvalidCoordinatesException.class,
                      NullOrEmptyCoordinatesException.class,
                     InvalidCoordinatesException.class})
  public ResponseEntity<Object> handleConflict(
      final RuntimeException ex, final WebRequest request
  ) {
    return ResponseEntity
        .status(HttpStatus.UNPROCESSABLE_ENTITY)
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .body(Problem
                  .builder()
                  .withTitle("Validation")
                  .with("errors", ex.getMessage())
                  .withStatus(Status.UNPROCESSABLE_ENTITY)
                  .build());
  }
}
