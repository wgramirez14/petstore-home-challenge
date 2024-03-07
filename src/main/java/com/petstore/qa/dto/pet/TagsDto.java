package com.petstore.qa.dto.pet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Jacksonized
@Builder
public class TagsDto {

  @JsonProperty("id")
  private int id;

  @JsonProperty("name")
  private String name;
}
