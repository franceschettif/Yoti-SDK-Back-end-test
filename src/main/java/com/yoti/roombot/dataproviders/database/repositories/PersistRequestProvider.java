package com.yoti.roombot.dataproviders.database.repositories;

import com.google.gson.Gson;

import com.yoti.roombot.core.model.RoomBotInitialState;
import com.yoti.roombot.core.usecases.PersistRequest;
import com.yoti.roombot.dataproviders.database.entitites.InputEntity;
import com.yoti.roombot.entrypoints.rest.responses.CleanResponse;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

@Service
public class PersistRequestProvider implements PersistRequest {

  private final BotPersistanceRepository repository;

  public PersistRequestProvider(final BotPersistanceRepository repository) {
    this.repository = repository;
  }

  @Override
  @Transactional
  public void persistRequest(final RoomBotInitialState input, final CleanResponse output) {

    repository.save(InputEntity.builder()
                        .requestTime(LocalDateTime.now())
                        .roomCoordsX(input.getRoom().getWidth())
                        .roomCoordsY(input.getRoom().getHeight())
                        .startX(input.getBot().getX())
                        .startY(input.getBot().getY())
                        .patchesCleaned(output.getPatches())
                        .patchesCoords(new Gson().toJson(input.getPatches()))
                        .build());

  }
}
