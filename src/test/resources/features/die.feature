Feature: Die Features

  Scenario: Retrieve die value as JSON
    Given the Yahtzee game is initialized
    When the client sends a GET request to "/die/1" with Accept header "application/json"
    Then the response status should be 200 OK
    And the response body should contain the die value in "json" format

  Scenario: Retrieve die value as Float
    Given the Yahtzee game is initialized
    When the client sends a GET request to "/die/2" with Accept header "application/vnd.yahtzee.float+json"
    Then the response status should be 200 OK
    And the response body should contain the die value in "float" format

  Scenario: Retrieve die value as Word
    Given the Yahtzee game is initialized
    When the client sends a GET request to "/die/3" with Accept header "application/vnd.yahtzee.word+json"
    Then the response status should be 200 OK
    And the response body should contain the die value in "word" format

  Scenario: Retrieve die value as Dots
    Given the Yahtzee game is initialized
    When the client sends a GET request to "/die/4" with Accept header "application/vnd.yahtzee.dots+json"
    Then the response status should be 200 OK
    And the response body should contain the die value in "dots" format

  Scenario: Retrieve die value as Integer (Fallback)
    Given the Yahtzee game is initialized
    When the client sends a GET request to "/die/5" with Accept header "application/vnd.yahtzee.int+json"
    Then the response status should be 200 OK
    And the response body should contain the die value in "json" format

  Scenario: Retrieve die value with invalid Accept header
    Given the Yahtzee game is initialized
    When the client sends a GET request to "/die/1" with Accept header "application/vnd.yahtzee.unknown+json"
    Then the response status should be 400 Bad Request
    And the response body should contain an error message

  Scenario: Successfully set die value
    Given the Yahtzee game is initialized
    When the client sends a PUT request to "/die" with a valid die ID and value
    Then the response status should be 204 No Content

  Scenario: Fail to set die value due to invalid authorization
    Given the Yahtzee game is initialized
    When the client sends a PUT request to "/die" with invalid Auth
    Then the response status should be 401 Unauthorized
    And the response body should contain an error message

  Scenario: Fail to set die value due to invalid die ID
    Given the Yahtzee game is initialized
    When the client sends a PUT request to "/die" with an invalid die ID of 6
    Then the response status should be 400 Bad Request
    And the response body should contain the error message "Die ID must be between 1 and 5"

  Scenario: Fail to set die value due to invalid die value
    Given the Yahtzee game is initialized
    When the client sends a PUT request to "/die" with an invalid die value of 7
    Then the response status should be 400 Bad Request
    And the response body should contain the error message "Die value must be between 1 and 6"

  Scenario: Fail to set die value due to invalid request body
    Given the Yahtzee game is initialized
    When the client sends a PUT request to "/die" with an invalid request body
    Then the response status should be 400 Bad Request
    And the response body should contain an error message

  Scenario: Successfully roll a die
    Given the Yahtzee game is initialized
    When the client sends a POST request to "/rollDie/1"
    Then the response status should be 200 OK
    And the response body should contain the new die value for die 1
    And the response should have a status of "success"

  Scenario: Fail to roll a die due to an invalid die ID
    Given the Yahtzee game is initialized
    When the client sends a POST request to "/rollDie/6"
    Then the response status should be 400 Bad Request
    And the response body should contain the error message "Die ID must be an integer between 1 and 5"
