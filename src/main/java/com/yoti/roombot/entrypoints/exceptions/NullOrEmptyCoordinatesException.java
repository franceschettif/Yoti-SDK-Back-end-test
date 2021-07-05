package com.yoti.roombot.entrypoints.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class NullOrEmptyCoordinatesException extends RuntimeException{

  public NullOrEmptyCoordinatesException(){
    super("No coordinates were given as input.");
  }
}

