Feature: Manage users
  As an admin
  I want to manage users
  So that I can control who access the app

  Scenario: Create a new user
    Given information about the new user
    When admin registers the new user
    Then user is registered successful

  Scenario: Create a list of new users
    Given there are 2 new users to be registered
    When admin registers the user list
    Then users are registered successful

  Scenario: Delete user
    Given user who is already registered
    When admin deletes the user
    Then user is deleted successful

  Scenario: Success log in
    Given user who is already registered
    When he logs in into the system
    Then user session is stared

  Scenario: Success log out
    Given user who is already registered
    And he is logged in
    When he logs out from the system
    Then user session is expired

  Scenario: Get an user by username
    Given user who is already registered
    When admin looks for the user
    Then user info is sent back to the admin

  Scenario: Update user info
    Given user who is already registered
    When he updates the info
    Then info is updated successful