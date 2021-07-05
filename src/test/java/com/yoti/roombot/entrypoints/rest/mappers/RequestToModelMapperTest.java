package com.yoti.roombot.entrypoints.rest.mappers;

import com.yoti.roombot.entrypoints.exceptions.InvalidCoordinatesException;
import com.yoti.roombot.entrypoints.exceptions.InvalidNumberOfItemsExeption;
import com.yoti.roombot.entrypoints.exceptions.NullOrEmptyCoordinatesException;
import com.yoti.roombot.entrypoints.rest.requests.CleanRequest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class RequestToModelMapperTest {

  private final RequestToModelMapper underTest = new RequestToModelMapper();

  @Test
  void shouldThrowNullOrEmptyCoordinatesWhenRoomIsNull() {
    Assertions.assertThatThrownBy(() -> underTest.map(new CleanRequest()))
        .isInstanceOf(NullOrEmptyCoordinatesException.class)
        .hasMessage("No coordinates were given as input.");
  }

  @Test
  void shouldThrowNullOrEmptyCoordinatesWhenRoomIsEmpty() {
    Assertions.assertThatThrownBy(() -> underTest.map(CleanRequest.builder().roomSize(List.of()).build()))
        .isInstanceOf(NullOrEmptyCoordinatesException.class)
        .hasMessage("No coordinates were given as input.");
  }

  @ParameterizedTest
  @MethodSource("invalidSize")
  void shouldThrowInvalidNumberOfItemsExeptionWhenRoomHasInvalidSize(List<Integer> roomSize) {
    Assertions.assertThatThrownBy(() -> underTest.map(CleanRequest.builder().roomSize(roomSize).build()))
        .isInstanceOf(InvalidNumberOfItemsExeption.class)
        .hasMessage("Number of items in the coordinates array is invalid.");
  }

  @ParameterizedTest
  @MethodSource("negativeCoords")
  void shouldThrowInvalidNumberOfItemsExeptionWhenRoomHasNegativeCoords(List<Integer> negativeRoom) {
    Assertions.assertThatThrownBy(() -> underTest.map(CleanRequest.builder().roomSize(negativeRoom).build()))
        .isInstanceOf(InvalidCoordinatesException.class)
        .hasMessage("Coordinates were either negative or out of bounds the perimeter of the room.");
  }

  @Test
  void shouldThrowNullOrEmptyCoordinatesWhenBotIsNull() {
    Assertions.assertThatThrownBy(() -> underTest.map(CleanRequest.builder().roomSize(List.of(2, 2)).coords(null).build()))
        .isInstanceOf(NullOrEmptyCoordinatesException.class)
        .hasMessage("No coordinates were given as input.");
  }


  @Test
  void shouldThrowNullOrEmptyCoordinatesWhenBotIsEmpty() {
    Assertions.assertThatThrownBy(() -> underTest.map(CleanRequest.builder().roomSize(List.of(2, 2)).coords(List.of()).build()))
        .isInstanceOf(NullOrEmptyCoordinatesException.class)
        .hasMessage("No coordinates were given as input.");
  }

  @ParameterizedTest
  @MethodSource("invalidSize")
  void shouldThrowInvalidNumberOfItemsExeptionWhenBotHasInvalidSize(List<Integer> botSize) {
    Assertions.assertThatThrownBy(() -> underTest.map(CleanRequest.builder().roomSize(List.of(2, 2)).coords(botSize).build()))
        .isInstanceOf(InvalidNumberOfItemsExeption.class)
        .hasMessage("Number of items in the coordinates array is invalid.");
  }

  @ParameterizedTest
  @MethodSource("negativeCoords")
  void shouldThrowInvalidNumberOfItemsExeptionWhenBotHasNegativeCoords(List<Integer> negativeBot) {
    Assertions.assertThatThrownBy(() -> underTest.map(CleanRequest.builder().roomSize(List.of(2, 2)).coords(negativeBot).build()))
        .isInstanceOf(InvalidCoordinatesException.class)
        .hasMessage("Coordinates were either negative or out of bounds the perimeter of the room.");
  }

  @Test
  void shouldThrowInvalidNumberOfItemsExeptionWhenBotHasCoordsOutOfTheRoomSize() {
    Assertions.assertThatThrownBy(() -> underTest.map(CleanRequest.builder().roomSize(List.of(2, 2)).coords(List.of(4, 4)).build()))
        .isInstanceOf(InvalidCoordinatesException.class)
        .hasMessage("Coordinates were either negative or out of bounds the perimeter of the room.");
  }


  @Test
  void shouldThrowNullOrEmptyCoordinatesWhenPatchesIsNull() {
    Assertions.assertThatThrownBy(() -> underTest.map(
        CleanRequest.builder().roomSize(List.of(2, 2)).coords(List.of(0, 0)).patches(null).build()))
        .isInstanceOf(NullOrEmptyCoordinatesException.class)
        .hasMessage("No coordinates were given as input.");
  }

  @Test
  void shouldThrowNullOrEmptyCoordinatesWhenPatchesIsEmpty() {
    Assertions.assertThatThrownBy(() -> underTest.map(
        CleanRequest.builder().roomSize(List.of(2, 2)).coords(List.of(0, 0)).patches(List.of()).build()))
        .isInstanceOf(NullOrEmptyCoordinatesException.class)
        .hasMessage("No coordinates were given as input.");
  }


  @Test
  void shouldThrowNullOrEmptyCoordinatesWhenOneOfThePatchesIsEmpty() {
    Assertions.assertThatThrownBy(() -> underTest.map(
        CleanRequest.builder().roomSize(List.of(2, 2)).coords(List.of(0, 0)).patches(List.of(List.of(1, 2), List.of(), List.of(2, 2))).build()))
        .isInstanceOf(NullOrEmptyCoordinatesException.class)
        .hasMessage("No coordinates were given as input.");
  }

  @ParameterizedTest
  @MethodSource("nullSet")
  void shouldThrowNullOrEmptyCoordinatesWhenPatchesIsNull(List<List<Integer>> nullSet) {
    Assertions.assertThatThrownBy(() -> underTest.map(
        CleanRequest.builder().roomSize(List.of(2, 2)).coords(List.of(0, 0)).patches(nullSet).build()))
        .isInstanceOf(NullOrEmptyCoordinatesException.class)
        .hasMessage("No coordinates were given as input.");

  }

  @ParameterizedTest
  @MethodSource("invalidSizeSet")
  void shouldThrowNullOrEmptyCoordinatesWhenPatchesHasInvalidSize(List<List<Integer>> invalidSizeSet) {
    Assertions.assertThatThrownBy(() -> underTest.map(
        CleanRequest.builder().roomSize(List.of(2, 2)).coords(List.of(0, 0)).patches(invalidSizeSet).build()))
        .isInstanceOf(InvalidNumberOfItemsExeption.class)
        .hasMessage("Number of items in the coordinates array is invalid.");
  }

  @ParameterizedTest
  @MethodSource("invalidCoordsSet")
  void shouldThrowNullOrEmptyCoordinatesWhenPatchesHasInval(List<List<Integer>> invalidCoordsSet) {
    Assertions.assertThatThrownBy(() -> underTest.map(
        CleanRequest.builder().roomSize(List.of(2, 2)).coords(List.of(0, 0)).patches(invalidCoordsSet).build()))
        .isInstanceOf(InvalidCoordinatesException.class)
        .hasMessage("Coordinates were either negative or out of bounds the perimeter of the room.");
  }

  @Test
  void shouldThrowNullOrEmptyCoordinatesWhenNavigationIsNull() {
    Assertions.assertThatThrownBy(() -> underTest.map(
        CleanRequest.builder().roomSize(List.of(2, 2)).coords(List.of(1, 1)).patches(List.of(List.of(0, 0))).instructions(null).build()))
        .isInstanceOf(NullOrEmptyCoordinatesException.class)
        .hasMessage("No coordinates were given as input.");
  }

  @Test
  void shouldThrowNullOrEmptyCoordinatesWhenNavigationIsEmpty() {
    Assertions.assertThatThrownBy(() -> underTest.map(
        CleanRequest.builder().roomSize(List.of(2, 2)).coords(List.of(1, 1)).patches(List.of(List.of(0, 0))).instructions("").build()))
        .isInstanceOf(NullOrEmptyCoordinatesException.class)
        .hasMessage("No coordinates were given as input.");
  }

  private static Stream<Arguments> emptySet(){
    return Stream.of(
        Arguments.of(List.of(List.of(1,2), List.of(), List.of(2,2)))
    );
  }

  private static Stream<Arguments> nullSet(){
    final ArrayList<List<Integer>> list = new ArrayList<>();
    list.add(List.of(1,2));
    list.add(null);
    list.add(List.of(2,2));
    return Stream.of(
        Arguments.of(list)
    );
  }

  private static Stream<Arguments> invalidSizeSet(){
    return Stream.of(
        Arguments.of(List.of(List.of(1,2), List.of(2,2,3))),
        Arguments.of(List.of(List.of(1,2), List.of(2)))
    );
  }

  private static Stream<Arguments> invalidCoordsSet(){
    return Stream.of(
        Arguments.of(List.of(List.of(1,2), List.of(3,3))),
        Arguments.of(List.of(List.of(1,2), List.of(2,4)))
    );
  }

  private static Stream<Arguments> invalidSize() {
    return Stream.of(
        Arguments.of(List.of(1, 2, 3)),
        Arguments.of(List.of(1))
    );
  }

  private static Stream<Arguments> negativeCoords() {
    return Stream.of(
        Arguments.of(List.of(-1, -5)),
        Arguments.of(List.of(-5, 4))
    );
  }
}
