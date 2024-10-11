Feature: Dice Features

  Scenario: Successfully retrieve the values of all five dice
    Given the Yahtzee game is initialized
    When the client sends a GET request to "/dice"
    Then the response status should be 200 OK
    And the response body should contain a list of five dice
    And each die should have a valid value between 1 and 6
    And the response should have a status of "success"

  Scenario: Successfully roll all five dice
    Given the Yahtzee game is initialized
    When the client sends a POST request to "/rollDice"
    Then the response status should be 200 OK
    And the response body should contain a list of five dice
    And each die should have a valid value between 1 and 6
    And the response should have a status of "success"

  Scenario: Roll all five dice and verify with get all dice response
    Given the Yahtzee game is initialized
    When the client sends a POST All Dice request to "/rollDice"
    Then the response status should be 200 OK
    And the response body should contain a list of five dice
    And each die should have a valid value between 1 and 6
    And the response should have a status of "success"

    When the client sends a GET All Dice request to "/dice"
    Then the response status should be 200 OK
    And the response body should contain a list of five dice
    And the dice values should match the rolled dice