package com.yoti.roombot.entrypoints.rest.controllers;

import com.yoti.roombot.core.usecases.CleaningRoutineUseCase;
import com.yoti.roombot.entrypoints.rest.mappers.RequestToModelMapper;
import com.yoti.roombot.entrypoints.rest.requests.CleanRequest;
import com.yoti.roombot.entrypoints.rest.responses.CleanResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/hoover/api")
@Api
public class RoomBotController {

  private final CleaningRoutineUseCase cleaningRoutineUseCase;
  private final RequestToModelMapper requestToModelMapper;

  public RoomBotController(final CleaningRoutineUseCase cleaningRoutineUseCase, final RequestToModelMapper requestToModelMapper) {
    this.cleaningRoutineUseCase = cleaningRoutineUseCase;
    this.requestToModelMapper = requestToModelMapper;
  }

  @ApiOperation(value = "cleanRoutine",
                notes = "The goal of the service is to take the room dimensions, the locations of the dirt patches, the hoover location and the driving instructions as input and to then output the following:\n"
                        + "\n"
                        + "*  The final hoover position (X, Y)\n"
                        + "*  The number of patches of dirt the robot cleaned up\n"
                        + "\n"
                        + "The service must persist every input and output to a database.")
  @ApiResponses( {
                     @ApiResponse(code = 200, message = "Success"),
                     @ApiResponse(code = 422, message = "Request is syntactically correct, but cannot be processed due to validation errors"),
                     @ApiResponse(code = 400, message = "Request is syntactically incorrect")
                 }
  )
  @PostMapping("/clean-the-room")
  public CleanResponse cleanRoutine(@RequestBody final CleanRequest cleanRequest) {
    return cleaningRoutineUseCase.doClean(requestToModelMapper.map(cleanRequest));

  }

}
