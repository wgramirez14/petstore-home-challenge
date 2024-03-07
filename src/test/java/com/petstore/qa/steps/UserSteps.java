package com.petstore.qa.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.petstore.qa.dto.user.UserDto;
import com.petstore.qa.services.common.ServiceManager;
import java.util.List;
import net.serenitybdd.annotations.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserSteps {

  private static final Logger logger = LoggerFactory.getLogger(PetSteps.class);

  @Step("Create user {}")
  public UserDto createSingleUser(UserDto userDto) {
    try {
      return ServiceManager.getUserService().createSingleUser(userDto);
    } catch (JsonProcessingException e) {
      logger.warn(e.getMessage());
      return null;
    }
  }

  @Step("Delete user {}")
  public void deleteUser(String username) {
    ServiceManager.getUserService().deleteUser(username);
  }

  @Step("Create a list of users")
  public List<UserDto> createUserList(List<UserDto> userListRequested) {
    try {
      return ServiceManager.getUserService().createUserList(userListRequested);
    } catch (JsonProcessingException e) {
      logger.warn(e.getMessage());
      return null;
    }
  }

  @Step("Log in user {}")
  public String logIn(UserDto userRequested) {
    return ServiceManager.getUserService().logIn(userRequested);
  }

  @Step("Log out")
  public String logOut() {
    return ServiceManager.getUserService().logOut();
  }

  @Step("Get an user with username {}")
  public UserDto getUserByUsername(String username) {
    try {
      return ServiceManager.getUserService().getUserByUsername(username);
    } catch (JsonProcessingException e) {
      logger.warn(e.getMessage());
      return null;
    }
  }

  @Step("Update user info")
  public UserDto updateUserInfo(String username, UserDto userRequested) {
    try {
      return ServiceManager.getUserService().updateUserInfo(username, userRequested);
    } catch (JsonProcessingException e) {
      logger.warn(e.getMessage());
      return null;
    }
  }
}
