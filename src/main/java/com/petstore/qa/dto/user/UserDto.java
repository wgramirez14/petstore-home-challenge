package com.petstore.qa.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class UserDto {
  @JsonProperty("id")
  private int id;

  @JsonProperty("username")
  private String username;

  @JsonProperty("firstName")
  private String firstName;

  @JsonProperty("lastName")
  private String lastName;

  @JsonProperty("email")
  private String email;

  @JsonProperty("password")
  private String password;

  @JsonProperty("phone")
  private String phone;

  @JsonProperty("userStatus")
  private int userStatus;
}
