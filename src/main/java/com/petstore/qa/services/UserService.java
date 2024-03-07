package com.petstore.qa.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.petstore.qa.dto.user.UserDto;
import com.petstore.qa.services.common.BaseService;
import io.restassured.response.Response;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService extends BaseService {

  private static final Logger logger = LoggerFactory.getLogger(UserService.class);

  public UserDto createSingleUser(UserDto userDto) throws JsonProcessingException {
    Response response =
        httpRequest
            .body(objectMapper.writeValueAsString(userDto))
            .post(properties.getProperty("user.createUser"));

    logger.info(MessageFormat.format("Response: {0}", response.body().asString()));
    return objectMapper.readValue(response.body().asString(), UserDto.class);
  }

  public List<UserDto> createUserList(List<UserDto> userListRequested)
      throws JsonProcessingException {
    Response response =
        httpRequest
            .body(objectMapper.writeValueAsString(userListRequested))
            .post(properties.getProperty("user.createListOfUsers"));
    logger.info(MessageFormat.format("Response: {0}", response.body().asString()));

    return objectMapper.readValue(
        response.body().asString(),
        objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, UserDto.class));
  }

  public void deleteUser(String username) {

    httpRequest.pathParam("username", username).delete(properties.getProperty("user.singleUser"));
  }

  public String logIn(UserDto userRequested) {
    return httpRequest
        .queryParam("username", userRequested.getUsername())
        .queryParam("password", userRequested.getPassword())
        .get(properties.getProperty("user.login"))
        .body()
        .asString();
  }

  public String logOut() {
    return httpRequest.get(properties.getProperty("user.logout")).body().asString();
  }

  public UserDto getUserByUsername(String username) throws JsonProcessingException {
    Response response =
        httpRequest.pathParam("username", username).get(properties.getProperty("user.singleUser"));

    logger.info(MessageFormat.format("Response: {0}", response.body().asString()));
    return objectMapper.readValue(response.body().asString(), UserDto.class);
  }

  public UserDto updateUserInfo(String username, UserDto newUserInfo)
      throws JsonProcessingException {
    Response response =
        httpRequest
            .pathParam("username", username)
            .body(objectMapper.writeValueAsString(newUserInfo))
            .put(properties.getProperty("user.singleUser"));
    logger.info(MessageFormat.format("Response: {0}", response.body().asString()));
    return objectMapper.readValue(response.body().asString(), UserDto.class);
  }
}
