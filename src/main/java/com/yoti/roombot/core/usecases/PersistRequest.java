package com.yoti.roombot.core.usecases;

import com.yoti.roombot.core.model.RoomBotInitialState;
import com.yoti.roombot.entrypoints.rest.responses.CleanResponse;

@FunctionalInterface
public interface PersistRequest {
  void persistRequest(RoomBotInitialState input, CleanResponse output);
}
