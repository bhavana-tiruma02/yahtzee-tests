package com.sas.automation.yahtzee.steps;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.Matchers.equalTo;

public class CheckYahtzeeSteps {


    @Given("all five dice have the same value")
    public void allFiveDiceHaveTheSameValue() {
        given()
                .auth().preemptive().basic("admin", "snakeeyes")
                .body("{ \"id\": 1, \"value\": 6 }").put("/die");
        given()
                .auth().preemptive().basic("admin", "snakeeyes")
                .body("{ \"id\": 2, \"value\": 6 }").put("/die");
        given()
                .auth().preemptive().basic("admin", "snakeeyes")
                .body("{ \"id\": 3, \"value\": 6 }").put("/die");

        given()
                .auth().preemptive().basic("admin", "snakeeyes")
                .body("{ \"id\": 4, \"value\": 6 }").put("/die");

        given()
                .auth().preemptive().basic("admin", "snakeeyes")
                .body("{ \"id\": 5, \"value\": 6 }").put("/die");
    }

    @Given("the five dice have different values")
    public void theFiveDiceHaveDifferentValues() {

        given()
                .auth().preemptive().basic("admin", "snakeeyes")
                .body("{ \"id\": 1, \"value\": 1 }").put("/die");
        given()
                .auth().preemptive().basic("admin", "snakeeyes")
                .body("{ \"id\": 2, \"value\": 2 }").put("/die");
        given()
                .auth().preemptive().basic("admin", "snakeeyes")
                .body("{ \"id\": 3, \"value\": 6 }").put("/die");

        given()
                .auth().preemptive().basic("admin", "snakeeyes")
                .body("{ \"id\": 4, \"value\": 6 }").put("/die");

        given()
                .auth().preemptive().basic("admin", "snakeeyes")
                .body("{ \"id\": 5, \"value\": 6 }").put("/die");
    }


    @Then("the response should indicate that Yahtzee is achieved")
    public void theResponseShouldIndicateYahtzeeIsAchieved() {
        then().body("status", equalTo("success"))
                .and().body("data", equalTo(true));
    }

    @Then("the response should indicate that Yahtzee is not achieved")
    public void theResponseShouldIndicateYahtzeeIsNotAchieved() {
        then().body("status", equalTo("success"))
                .and().body("data", equalTo(false));
    }
}
