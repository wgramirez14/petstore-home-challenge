package com.petstore.qa.dto.pet;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class PetsResponseDto {

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("category")
  private CategoryDto category;

  @JsonProperty("name")
  private String name;

  @JsonProperty("photoUrls")
  private ArrayList<String> photoUrls;

  @JsonProperty("tags")
  private ArrayList<TagsDto> tags;

  @JsonProperty("status")
  private String status;
}
