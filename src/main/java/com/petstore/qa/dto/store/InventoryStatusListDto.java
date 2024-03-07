package com.petstore.qa.dto.store;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class InventoryStatusListDto {

  @JsonProperty("approved")
  private int approved;

  @JsonProperty("placed")
  private int placed;

  @JsonProperty("delivered")
  private int delivered;
}
