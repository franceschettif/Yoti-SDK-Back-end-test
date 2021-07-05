package com.yoti.roombot.core.mappers;

import com.yoti.roombot.core.model.Point;
import com.yoti.roombot.core.model.Room;
import com.yoti.roombot.entrypoints.exceptions.InvalidCoordinatesException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RoomTest {

  private final Room underTest = new Room(3, 3);

  @Test
  void shouldApplyDirtPatch(){
    underTest.applyDirtPatch(new Point(1, 1));
    Assertions.assertThat(underTest.isTileDirty(new Point(1, 1))).isTrue();
  }

  @Test
  void shouldReturnFalseIfTileIsNotDirty(){
    underTest.applyDirtPatch(new Point(1, 1));
    Assertions.assertThat(underTest.isTileDirty(new Point(1, 2))).isFalse();
  }

  @Test
  void shouldThrowInvalidCoordinatesExceptionWhenPointIsOutOfRoomRange(){
    Assertions.assertThatThrownBy(() -> underTest.applyDirtPatch(new Point(3, 4)))
        .isInstanceOf(InvalidCoordinatesException.class)
        .hasMessage("Coordinates were either negative or out of bounds the perimeter of the room.");
  }

}
