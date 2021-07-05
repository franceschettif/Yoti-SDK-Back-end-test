package com.yoti.roombot.core.model;

import java.util.Arrays;

public enum BotDirection {
  NORTH('N'),
  SOUTH('S'),
  EAST('E'),
  WEST('W');

  private final char code;

  BotDirection(final char code) {
    this.code = code;
  }

  public static BotDirection byCode(char c) {
    return Arrays.stream(BotDirection.values())
        .filter(it -> it.code == c)
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}
