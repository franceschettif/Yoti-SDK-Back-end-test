package com.yoti.roombot.core.model;

import com.yoti.roombot.entrypoints.exceptions.InvalidCoordinatesException;
import com.yoti.roombot.entrypoints.exceptions.InvalidNumberOfItemsExeption;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomDimension {

  private int width;
  private int height;

  public RoomDimension (final Integer x, final Integer y){
    this.width=x;
    this.height=y;
  }

  public void checkValidity(final List<Integer> dimensions){
    if(dimensions.get(0) <=0 || dimensions.get(1) <=0) {
      throw new InvalidCoordinatesException();
    }
  }

}
