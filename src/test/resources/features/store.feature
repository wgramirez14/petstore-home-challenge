Feature: Manage Store
  As an user
  I want to check out the store
  So that I can manage the inventory list

  Background:
    Given a new order to be placed

  Scenario: Get pet inventories by status
    When the user look for them
    Then inventory is listed by status

  Scenario: Place an order
    When the user add the new order
    Then new order is created successful

  Scenario: Delete purchase order
    Given the user add the new order
    When user deletes the purchase order
    Then new order is deleted successful

  Scenario: Find purchase order by ID
    Given the user add the new order
    When the user look for the purchase created
    Then the order is listed