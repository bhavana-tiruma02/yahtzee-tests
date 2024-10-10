Feature: Check if Yahtzee state is achieved

  Scenario: All dice show the same value (Yahtzee achieved)
    Given the Yahtzee game is initialized
    And all five dice have the same value
    When the client sends a GET request to "/isYahtzee"
    Then the response status should be 200 OK
    And the response should indicate that Yahtzee is achieved

  Scenario: All dice do not show the same value (Yahtzee not achieved)
    Given the Yahtzee game is initialized
    And the five dice have different values
    When the client sends a GET request to "/isYahtzee"
    Then the response status should be 200 OK
    And the response should indicate that Yahtzee is not achieved
