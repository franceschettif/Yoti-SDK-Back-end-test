package com.yoti.roombot.core.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Data
public class Point {
  public int x, y;

  public Point(final Point otherPoint) {
    this.x = otherPoint.x;
    this.y = otherPoint.y;
  }

  public Point(final Integer x, final Integer y){
    this.x = x;
    this.y = y;
  }

  public void incrementX() {
    this.x++;
  }
  public void incrementY() {
    this.y++;
  }
  public void decrementX() {
    this.x--;
  }
  public void decrementY() {
    this.y--;
  }

  public List<Integer> toList() {
    return List.of(x, y);
  }

  public Boolean isValid(final List<Integer> dimension){
    return x >= 0 && x <= dimension.get(0) && y >= 0 && y <= dimension.get(1);
  }

  public static Point createFromCoords(final List<Integer> coordinates){
    return new Point(coordinates.get(0), coordinates.get(1));
  }
}
