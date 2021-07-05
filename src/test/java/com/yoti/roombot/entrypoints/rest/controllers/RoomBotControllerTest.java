package com.yoti.roombot.entrypoints.rest.controllers;

import com.yoti.roombot.entity.ErrorResponse;
import com.yoti.roombot.entrypoints.rest.requests.CleanRequest;
import com.yoti.roombot.entrypoints.rest.responses.CleanResponse;
import com.yoti.roombot.RoomBotApplication;

import org.apache.tomcat.jni.Error;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import lombok.Data;

@SpringBootTest(classes = RoomBotApplication.class, webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class RoomBotControllerTest {
  @Autowired
  private TestRestTemplate restTemplate;




  @Test
  void testWithProvidedInput() {
    final CleanRequest cleanRequest = new CleanRequest();
    cleanRequest.setRoomSize(List.of(5, 5));
    cleanRequest.setCoords(List.of(1, 2));
    cleanRequest.setPatches(List.of(
        List.of(1, 0),
        List.of(2, 2),
        List.of(2, 3)
    ));
    cleanRequest.setInstructions("NNESEESWNWW");
    final ResponseEntity<CleanResponse> outputDetails = restTemplate.postForEntity("/hoover/api/clean-the-room", cleanRequest, CleanResponse.class);
    Assertions.assertThat(outputDetails.getStatusCode()).isEqualTo(HttpStatus.OK);
    final CleanResponse body = outputDetails.getBody();

    Assertions.assertThat(body).isNotNull();
    Assertions.assertThat(body.getPatches()).isEqualTo(1);
    Assertions.assertThat(body.getCoords()).isEqualTo(List.of(1, 3));
  }

  @Test
  void testWithAnotherInput() {
    final CleanRequest cleanRequest = new CleanRequest();
    cleanRequest.setRoomSize(List.of(5, 5));
    cleanRequest.setCoords(List.of(0, 0));
    cleanRequest.setPatches(List.of(
        List.of(0, 0),
        List.of(1, 1),
        List.of(2, 2),
        List.of(3, 3)
    ));
    cleanRequest.setInstructions("NENENE");
    final ResponseEntity<CleanResponse> outputDetails = restTemplate.postForEntity("/hoover/api/clean-the-room", cleanRequest, CleanResponse.class);
    Assertions.assertThat(outputDetails.getStatusCode()).isEqualTo(HttpStatus.OK);
    final CleanResponse body = outputDetails.getBody();

    Assertions.assertThat(body).isNotNull();
    Assertions.assertThat(body.getPatches()).isEqualTo(4);
    Assertions.assertThat(body.getCoords()).isEqualTo(List.of(3, 3));
  }

  @Test
  void testWithOutOfBoundPatchesThrowsException() {
    final CleanRequest cleanRequest = new CleanRequest();
    cleanRequest.setRoomSize(List.of(3, 3));
    cleanRequest.setCoords(List.of(0, 0));
    cleanRequest.setPatches(List.of(
        List.of(3, 4)
    ));
    cleanRequest.setInstructions("NENENE");
    final ResponseEntity<ErrorResponse> outputDetails = restTemplate.postForEntity("/hoover/api/clean-the-room", cleanRequest, ErrorResponse.class);
    Assertions.assertThat(outputDetails.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    final ErrorResponse body = outputDetails.getBody();

    Assertions.assertThat(body).isNotNull();
    Assertions.assertThat(body.getDetail()).isEqualTo("Coordinates were either negative or out of bounds the perimeter of the room.");
  }

  @Test
  void testWithNoRoomDimensionsShouldThrowException() {
    final CleanRequest cleanRequest = new CleanRequest();
    cleanRequest.setRoomSize(null);
    cleanRequest.setCoords(List.of(1, 2));
    cleanRequest.setPatches(List.of(
        List.of(1, 0),
        List.of(2, 2),
        List.of(2, 3)
    ));
    cleanRequest.setInstructions("NNESEESWNWW");
    final ResponseEntity<ErrorResponse> outputDetails = restTemplate.postForEntity("/hoover/api/clean-the-room", cleanRequest, ErrorResponse.class);
    Assertions.assertThat(outputDetails.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    final ErrorResponse body = outputDetails.getBody();

    Assertions.assertThat(body).isNotNull();
    Assertions.assertThat(body.getDetail()).isEqualTo("No coordinates were given as input.");
  }
}
