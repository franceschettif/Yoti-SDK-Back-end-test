package com.yoti.roombot.core.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BotTest {

  private final Bot cornerBot = new Bot(new Point(0,0));
  private final Bot centerBot = new Bot(new Point(1,1));

  @Test
  void shouldMoveUp(){
    Assertions.assertThat(centerBot.getPosition()).isEqualTo(new Point(1,1));
    centerBot.move(BotDirection.NORTH, new Room(3,3));
    Assertions.assertThat(centerBot.getPosition()).isEqualTo(new Point(1,2));
  }
  @Test
  void shouldMoveDown(){
    Assertions.assertThat(centerBot.getPosition()).isEqualTo(new Point(1,1));
    centerBot.move(BotDirection.SOUTH, new Room(3,3));
    Assertions.assertThat(centerBot.getPosition()).isEqualTo(new Point(1,0));
  }
  @Test
  void shouldMoveRight(){
    Assertions.assertThat(centerBot.getPosition()).isEqualTo(new Point(1,1));
    centerBot.move(BotDirection.EAST, new Room(3,3));
    Assertions.assertThat(centerBot.getPosition()).isEqualTo(new Point(2,1));
  }
  @Test
  void shouldMoveLeft(){
    Assertions.assertThat(centerBot.getPosition()).isEqualTo(new Point(1,1));
    centerBot.move(BotDirection.WEST, new Room(3,3));
    Assertions.assertThat(centerBot.getPosition()).isEqualTo(new Point(0,1));
  }

  @Test
  void shouldNotMoveUp(){
    Assertions.assertThat(cornerBot.getPosition()).isEqualTo(new Point(0,0));
    cornerBot.move(BotDirection.NORTH, new Room(0,0));
    Assertions.assertThat(cornerBot.getPosition()).isEqualTo(new Point(0,0));
  }
  @Test
  void shouldNotMoveDown(){
    Assertions.assertThat(cornerBot.getPosition()).isEqualTo(new Point(0,0));
    cornerBot.move(BotDirection.SOUTH, new Room(0,0));
    Assertions.assertThat(cornerBot.getPosition()).isEqualTo(new Point(0,0));
  }
  @Test
  void shouldNotMoveRight(){
    Assertions.assertThat(cornerBot.getPosition()).isEqualTo(new Point(0,0));
    cornerBot.move(BotDirection.EAST, new Room(0,0));
    Assertions.assertThat(cornerBot.getPosition()).isEqualTo(new Point(0,0));
  }
  @Test
  void shouldNotMoveLeft(){
    Assertions.assertThat(cornerBot.getPosition()).isEqualTo(new Point(0,0));
    cornerBot.move(BotDirection.WEST, new Room(0,0));
    Assertions.assertThat(cornerBot.getPosition()).isEqualTo(new Point(0,0));
  }

}
