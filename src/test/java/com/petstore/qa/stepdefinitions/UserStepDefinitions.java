package com.petstore.qa.stepdefinitions;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.matchesPattern;

import com.petstore.qa.common.BaseStepDefinitions;
import com.petstore.qa.dto.user.UserDto;
import com.petstore.qa.steps.UserSteps;
import com.petstore.qa.utils.DataGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import net.serenitybdd.annotations.Steps;
import org.apache.http.HttpStatus;

public class UserStepDefinitions extends BaseStepDefinitions {

  private UserDto userRequested, userResponse;
  private List<UserDto> userListRequested, userListResponse;
  private String userSession;

  @Steps private UserSteps userSteps;

  @Given("information about the new user")
  public void informationAboutTheNewUser() {
    userRequested = DataGenerator.generateRandomUserInfo();
  }

  @Given("there are {} new users to be registered")
  public void aSetOfNewUsers(int amountOfUsers) {
    userListRequested = new ArrayList<>();
    IntStream.range(0, amountOfUsers)
        .forEach(x -> userListRequested.add(DataGenerator.generateRandomUserInfo()));
  }

  @Given("user who is already registered")
  public void userWhichIsAlreadyRegistered() {
    informationAboutTheNewUser();
    adminRegistersTheNewUser();
  }

  @When("admin registers the new user")
  public void adminRegistersTheNewUser() {
    userResponse = userSteps.createSingleUser(userRequested);
  }

  @When("admin deletes the user")
  public void adminDeletesUser() {
    userSteps.deleteUser(userRequested.getUsername());
  }

  @When("admin registers the user list")
  public void adminRegistersTheUserList() {
    userListResponse = userSteps.createUserList(userListRequested);
  }

  @When("(he logs in into the system)(he is logged in)")
  public void heLogsInIntoTheSystem() {
    userSession = userSteps.logIn(userRequested);
  }

  @When("he logs out from the system")
  public void heLogsOutFromTheSystem() {
    userSession = userSteps.logOut();
  }

  @When("admin looks for the user")
  public void adminLooksForTheUser() {
    userResponse = userSteps.getUserByUsername(userResponse.getUsername());
  }

  @When("he updates the info")
  public void heUpdatesTheInfo() {
    userRequested = DataGenerator.generateRandomUserInfo();
    userResponse = userSteps.updateUserInfo(userResponse.getUsername(), userRequested);
  }

  @Then("user is registered successful")
  public void userIsRegisteredSuccessful() {
    validateResponse(HttpStatus.SC_OK, "schemas/user.json");
    validateUserInfo();
    adminDeletesUser();
  }

  @Then("users are registered successful")
  public void usersAreRegisteredSuccessful() {
    validateResponse(HttpStatus.SC_OK, "schemas/userList.json");

    for (int i = 0; i < userListResponse.size(); i++) {
      userRequested = userListRequested.get(i);
      userResponse = userListResponse.get(i);
      validateUserInfo();
      adminDeletesUser();
    }
  }

  @Then("user is deleted successful")
  public void userIsDeletedSuccessful() {
    validateResponse(HttpStatus.SC_OK, null);
  }

  @Then("user session is stared")
  public void userSessionIsStared() {
    validateResponse(HttpStatus.SC_OK, null);
    assertThat(userSession, matchesPattern("^Logged in user session: [0-9]+$"));
    adminDeletesUser();
  }

  @Then("user session is expired")
  public void userSessionIsExpired() {
    validateResponse(HttpStatus.SC_OK, null);
    assertThat(userSession, matchesPattern("^User logged out$"));
    adminDeletesUser();
  }

  @Then("user info is sent back to the admin")
  public void userInfoIsSentBackToTheAdmin() {
    validateResponse(HttpStatus.SC_OK, "schemas/user.json");
    adminDeletesUser();
  }

  @Then("info is updated successful")
  public void infoIsUpdatedSuccessful() {
    validateResponse(HttpStatus.SC_OK, "schemas/user.json");
    adminLooksForTheUser();
    validateUserInfo();
    adminDeletesUser();
  }

  private void validateUserInfo() {
    assertThat(userRequested.getId(), equalTo(userResponse.getId()));
    assertThat(userRequested.getUsername(), equalTo(userResponse.getUsername()));
    assertThat(userRequested.getFirstName(), equalTo(userResponse.getFirstName()));
    assertThat(userRequested.getLastName(), equalTo(userResponse.getLastName()));
    assertThat(userRequested.getEmail(), equalTo(userResponse.getEmail()));
    assertThat(userRequested.getPassword(), equalTo(userResponse.getPassword()));
    assertThat(userRequested.getPhone(), equalTo(userResponse.getPhone()));
    assertThat(userRequested.getUserStatus(), equalTo(userResponse.getUserStatus()));
  }
}
