package com.petstore.qa.contracts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.petstore.qa.dto.user.UserDto;
import java.util.List;

public interface UserService {

  UserDto createSingleUser(UserDto userDto) throws JsonProcessingException;

  List<UserDto> createUserList(List<UserDto> userListRequested) throws JsonProcessingException;

  void deleteUser(String username);

  String logIn(UserDto userRequested);

  String logOut();

  UserDto getUserByUsername(String username) throws JsonProcessingException;

  UserDto updateUserInfo(String username, UserDto newUserInfo) throws JsonProcessingException;
}
