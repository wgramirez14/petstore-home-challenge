package com.petstore.qa.dto.pet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class CategoryDto {

  @JsonProperty("id")
  private int id;

  @JsonProperty("name")
  private String name;

  @Override
  public String toString() {
    return "\n" + "id: " + id + "\n" + "name: " + name + "\n";
  }
}
