package com.yoti.roombot.core.usecases;

import com.yoti.roombot.core.model.BotDirection;
import com.yoti.roombot.core.model.RoomBotInitialState;
import com.yoti.roombot.core.model.Room;
import com.yoti.roombot.core.model.Bot;
import com.yoti.roombot.core.model.Point;
import com.yoti.roombot.entrypoints.rest.responses.CleanResponse;

import org.springframework.stereotype.Service;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CleaningRoutineUseCase {
  private final PersistRequest persistRequest;

  public CleanResponse doClean(final RoomBotInitialState inputDetails) {

    // create room
    final Room room = new Room(inputDetails.getRoom().getWidth(), inputDetails.getRoom().getHeight());
    // apply dirt patches
    inputDetails.getPatches().forEach(x ->
                                          room.applyDirtPatch(new Point(x.x, x.y))
    );

    // establish current bot position
    final Point currentPosition = new Point(inputDetails.getBot());
    final Bot bot = new Bot(currentPosition);
    // initial tile
    if (room.isTileDirty(currentPosition)) {
      room.clean(currentPosition);
    }

    // move the bot
    final List<BotDirection> botDirections = inputDetails.getBotDirections();
    for (final BotDirection direction : botDirections) {
      bot.move(direction, room);
      if (room.isTileDirty(bot.getPosition())) {
        room.clean(bot.getPosition());
      }

    }
    final CleanResponse output = CleanResponse.builder()
        .coords(bot.getPosition().toList())
        .patches(room.getTotalCleaned())
        .build();

    persistRequest.persistRequest(inputDetails, output);
    return output;
  }
}
