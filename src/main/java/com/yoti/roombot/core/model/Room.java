package com.yoti.roombot.core.model;

import com.yoti.roombot.entrypoints.exceptions.InvalidCoordinatesException;

import lombok.SneakyThrows;

public class Room {
  private int totalCleaned;
  private final RoomTile[][] tiles;
  private final Integer roomWidth;
  private final Integer roomHeight;

  public int getTotalCleaned() {
    return totalCleaned;
  }

  public Room(final Integer roomX, final Integer roomY){
    roomWidth = roomX;
    roomHeight = roomY;
    tiles = new RoomTile[roomX][roomY];

    for (int x = 0; x < roomX; x ++) {
      for (int y = 0; y < roomY; y++) {
        tiles[x][y] = RoomTile.TILE_CLEAN;
      }
    }
  }


  public boolean contains(final Point p) {
    return p.x >= 0 && p.x < roomWidth && p.y >= 0 && p.y < roomHeight;
  }

  public void clean(final Point p) {
    this.totalCleaned++;
    tiles[p.x][p.y] = RoomTile.TILE_CLEAN;
  }

  @SneakyThrows
  public void applyDirtPatch(final Point point){
    if(point.getX() > roomWidth || point.getY() > roomHeight) {
      throw new InvalidCoordinatesException();
    }
    tiles[point.getX()][point.getY()] = RoomTile.TILE_DIRTY;
  }

  public boolean isTileDirty(final Point position){
    return tiles[position.x][position.y] == RoomTile.TILE_DIRTY;
  }



}
