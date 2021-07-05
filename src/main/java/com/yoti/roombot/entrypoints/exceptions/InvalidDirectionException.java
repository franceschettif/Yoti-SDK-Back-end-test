package com.yoti.roombot.entrypoints.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidDirectionException extends RuntimeException{

  public InvalidDirectionException(final char direction, final Throwable t){
    super("Invalid direction provided: " + direction);
  }
}
