Feature: Manage pets
  As an User
  I want to manage my pets registered
  So that I can generate orders for pets

  Scenario Outline: Find pets by status
    Given there are pets registered
    When the user search for the pets with the status <status>
    Then all pets are listed according to the status <status>
    Examples:
      | status    |
      | available |
      | pending   |
      | sold      |

  Scenario Outline: Find pets by tags
    Given there are pets registered
    When the user search for the pets by tags <tags>
    Then all pets are listed according to the tags <tags> defined
    Examples:
      | tags           |
      | tag1,tag2,tag3 |
      | tag3           |

  Scenario: Find a pet by ID
    Given there are pets registered
    When the user search for a pet by ID
    Then the pet is listed

  Scenario: Pet not found
    Given there are pets registered
    When the user search for a pet that is not being registered
    Then the pet is not found

  Scenario: Add a new pet
    Given a new pet to be added
    When user registers the new pet
    Then the new pet is registered successful

  Scenario: Add a new pet with incomplete info
    Given a new pet to be added with missing info
    When user registers the new pet
    Then the new pet is not registered

  Scenario: Add an existing pet
    Given a pet that already exists
    When user registers the pet already registered
    Then the new pet is updated successful

  Scenario: Delete a pet
    Given a pet that already exists
    When the user delete the pet
    Then pet info is no longer present

  Scenario: Edit an existing pet
    Given a pet that already exists
    When user edits the pet
    Then the new pet is updated successful