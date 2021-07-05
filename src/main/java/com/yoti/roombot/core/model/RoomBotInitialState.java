package com.yoti.roombot.core.model;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RoomBotInitialState {

  private RoomDimension room;
  private Point bot;
  private List<Point> patches;
  private List<BotDirection> botDirections;
}
