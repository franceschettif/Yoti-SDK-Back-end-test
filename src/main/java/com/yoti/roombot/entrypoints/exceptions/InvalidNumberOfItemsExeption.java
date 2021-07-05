package com.yoti.roombot.entrypoints.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidNumberOfItemsExeption extends RuntimeException{

  public InvalidNumberOfItemsExeption(){
    super("Number of items in the coordinates array is invalid.");
  }
}
