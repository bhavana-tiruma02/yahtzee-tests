Feature: Player Name

  Scenario: Successfully retrieve the player's name
    Given the Yahtzee game is initialized
    When the client sends a GET request to "/playerName"
    Then the response status should be 200 OK
    And the response body should contain the player's name


  Scenario: Successfully set a new player's name
    Given the Yahtzee game is initialized
    When the client sends a PUT request to "/playerName" with the name "John"
    Then the response status should be 204 No Content
    And the player's name should be updated to "John"

  Scenario: Fail to set the player's name due to missing authorization
    Given the Yahtzee game is initialized
    When the client sends a PUT request to "/playerName" without authorization
    Then the response status should be 401 Unauthorized
    And the response body should contain an error message "Basic auth missing or could not be parsed"

  Scenario: Fail to set the player's name due to invalid authorization
    Given the Yahtzee game is initialized
    When the client sends a PUT request to "/playerName" with invalid authorization
    Then the response status should be 401 Unauthorized
    And the response body should contain an error message "Basic auth missing or could not be parsed"

  Scenario: Fail to set the player's name due to invalid JSON body
    Given the Yahtzee game is initialized
    When the client sends a PUT request to "/playerName" with an invalid body
    Then the response status should be 400 Bad Request
    And the response body should contain an error message "Invalid request body format"
