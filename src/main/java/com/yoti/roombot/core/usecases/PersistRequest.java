package com.yoti.roombot.core.usecases;

import com.yoti.roombot.core.model.BotInitializer;
import com.yoti.roombot.entrypoints.rest.responses.CleanResponse;

public interface PersistRequest {
  void persistRequest(BotInitializer input, CleanResponse output);
}
