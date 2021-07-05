package com.yoti.roombot.entrypoints.rest.requests;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CleanRequest {
  @ApiModelProperty(example = "[5,5]")
  private List<Integer> roomSize;
  @ApiModelProperty(example = "[1,2]")
  private List<Integer> coords;
  @ApiModelProperty(example = "[\n"
                              + "    [1, 0],\n"
                              + "    [2, 2],\n"
                              + "    [2, 3]\n"
                              + "  ]")
  private List<List<Integer>> patches;
  @ApiModelProperty(example = "NNESEESWNWW")
  private String instructions;

}
