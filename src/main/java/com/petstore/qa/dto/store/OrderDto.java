package com.petstore.qa.dto.store;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class OrderDto {

  @JsonProperty("id")
  private int id;

  @JsonProperty("petId")
  private int petId;

  @JsonProperty("quantity")
  private int quantity;

  @JsonProperty("shipDate")
  private String shipDate;

  @JsonProperty("status")
  private String status;

  @JsonProperty("complete")
  private boolean complete;
}
