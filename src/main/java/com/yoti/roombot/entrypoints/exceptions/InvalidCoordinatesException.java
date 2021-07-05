package com.yoti.roombot.entrypoints.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidCoordinatesException extends RuntimeException{

  public InvalidCoordinatesException(){
    super("Coordinates were either negative or out of bounds the perimeter of the room.");
  }
}
