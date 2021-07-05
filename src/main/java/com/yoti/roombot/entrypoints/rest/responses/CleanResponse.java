package com.yoti.roombot.entrypoints.rest.responses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CleanResponse {
  private List<Integer> coords;
  private int patches;
}
