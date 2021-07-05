package com.yoti.roombot.entrypoints.rest.mappers;

import com.yoti.roombot.core.model.BotDirection;
import com.yoti.roombot.core.model.RoomBotInitialState;
import com.yoti.roombot.core.model.Point;
import com.yoti.roombot.core.model.RoomDimension;
import com.yoti.roombot.entrypoints.exceptions.InvalidCoordinatesException;
import com.yoti.roombot.entrypoints.exceptions.InvalidDirectionException;
import com.yoti.roombot.entrypoints.exceptions.InvalidNumberOfItemsExeption;
import com.yoti.roombot.entrypoints.exceptions.NullOrEmptyCoordinatesException;
import com.yoti.roombot.entrypoints.rest.requests.CleanRequest;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RequestToModelMapper {

  public RoomBotInitialState map(final CleanRequest cleanRequest){
    return RoomBotInitialState.builder()
        .room(validateRoomCoords(cleanRequest.getRoomSize()))
        .bot(validateBotStartingCoords(cleanRequest.getCoords(), cleanRequest.getRoomSize()))
        .patches(validatePatchesCoords(cleanRequest.getPatches(), cleanRequest.getRoomSize()))
        .botDirections(validateDirections(cleanRequest.getInstructions()))
        .build();
  }

  private RoomDimension validateRoomCoords(final List<Integer> roomCoordinates){
    if(CollectionUtils.isEmpty(roomCoordinates)){
      throw new NullOrEmptyCoordinatesException();
    }

    if(roomCoordinates.size() != 2) {
      throw new InvalidNumberOfItemsExeption();
    }

    final RoomDimension roomDimension = new RoomDimension(roomCoordinates.get(0), roomCoordinates.get(1));
    roomDimension.checkValidity(roomCoordinates);
    return roomDimension;
  }

  private Point validateBotStartingCoords(final List<Integer> botCoords, final List<Integer> roomCoords){
    if(CollectionUtils.isEmpty(botCoords)){
      throw new NullOrEmptyCoordinatesException();
    }

    if(botCoords.size() != 2) {
      throw new InvalidNumberOfItemsExeption();
    }

    final Point botInitialPosition = Point.createFromCoords(botCoords);
    if(!botInitialPosition.isValid(roomCoords)) {
      throw new InvalidCoordinatesException();
    }
    return botInitialPosition;
  }

  private List<Point> validatePatchesCoords(final List<List<Integer>> patchesCoords, final List<Integer> roomCoords){
    if(CollectionUtils.isEmpty(patchesCoords)
       || patchesCoords.stream().anyMatch(Objects::isNull)
       || patchesCoords.stream().anyMatch(List::isEmpty)){
      throw new NullOrEmptyCoordinatesException();
    }

    if(patchesCoords.stream().anyMatch(x -> x.size() !=2)){
      throw new InvalidNumberOfItemsExeption();
    }

    final List<Point> points = patchesCoords.stream().map(Point::createFromCoords).collect(Collectors.toList());

    points.forEach(x ->{
      if(!x.isValid(roomCoords)) {
        throw new InvalidCoordinatesException();
      }
    });

    return points;
  }

  private List<BotDirection> validateDirections(final String directions){
    if(!StringUtils.hasLength(directions)){
      throw new NullOrEmptyCoordinatesException();
    }
    final char[] directionArray = directions.toCharArray();

    final List<BotDirection> botDir = new ArrayList<>();
    for (final char directionChar : directionArray) {
      try{
      botDir.add(BotDirection.byCode(directionChar));}
      catch (final IllegalArgumentException ex){
        throw new InvalidDirectionException(directionChar, ex);
      }
    }
    return botDir;
  }
}
