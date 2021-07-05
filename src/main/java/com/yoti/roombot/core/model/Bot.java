package com.yoti.roombot.core.model;

public class Bot {
  private Point position;

  public Bot(final Point position) {
    this.position = position;
  }

  public Point getPosition() {
    return position;
  }

  public void move(final BotDirection instruction, final Room room) {
    final Point newPosition = new Point(position);
    switch (instruction) {
      case NORTH:
        newPosition.incrementY();
        break;
      case EAST:
        newPosition.incrementX();
        break;
      case SOUTH:
        newPosition.decrementY();
        break;
      case WEST:
        newPosition.decrementX();
        break;
    }

    if ( room.contains(newPosition) ) {
      this.position = newPosition;
    }
  }
}
