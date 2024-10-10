package com.sas.automation.yahtzee.steps;

import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.core.Serenity;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

public class PlayerSteps {

    @Given("the Yahtzee game is initialized")
    public void theGameIsInitialized() {
        Serenity.recordReportData().withTitle("Game Initialization").andContents("Game initialized successfully.");
    }

    @When("the client sends a GET request to {string}")
    public void theClientSendsAGetRequestTo(String endpoint) {
        SerenityRest.when().get(endpoint);
    }

    @Then("the response status should be {int} OK")
    public void theResponseStatusShouldBeOK(int statusCode) {
        SerenityRest.then().statusCode(statusCode);
    }

    @And("the response body should contain the player's name")
    public void theResponseBodyShouldContainPlayerName() {
        SerenityRest.then().body("status", equalTo("success"));
    }

    @And("the response body should contain an error message {string}")
    public void theResponseBodyShouldContainErrorMessage(String errorMessage) {
        SerenityRest.then().body("status", equalTo("failed"))
                .and().body("data", equalTo(errorMessage));
    }



    @When("the client sends a PUT request to {string} with the name {string}")
    public void theClientSendsAPutRequestTo(String endpoint, String playerName) {
        SerenityRest.given()
                .contentType(JSON)
                .auth().preemptive().basic("admin", "snakeeyes")
                .body("{ \"name\": \"" + playerName + "\" }")
                .when()
                .put(endpoint);
    }


    @Then("the response status should be {int} No Content")
    public void theResponseStatusShouldBeNoContent(int statusCode) {
        SerenityRest.then().statusCode(statusCode);
    }

    @And("the player's name should be updated to {string}")
    public void thePlayerNameShouldBeUpdated(String expectedName) {
        Serenity.recordReportData().withTitle("Player Update").andContents("Player name updated to " + expectedName);
    }

    @When("the client sends a PUT request to {string} without authorization")
    public void theClientSendsAPutRequestWithoutAuthorization(String endpoint) {
        SerenityRest.given()
                .contentType(JSON)
                .body("{ \"name\": \"John\" }")
                .when()
                .put(endpoint);
    }

    @When("the client sends a PUT request to {string} with invalid authorization")
    public void theClientSendsAPutRequestWithInvalidAuthorization(String endpoint) {
        SerenityRest.given()
                .contentType(JSON)
                .body("{ \"name\": \"John\" }")
                .when()
                .put(endpoint);
    }

    @Then("the response status should be {int} Unauthorized")
    public void theResponseStatusShouldBeUnauthorized(int statusCode) {
        SerenityRest.then().statusCode(statusCode);
    }

    @When("the client sends a PUT request to {string} with an invalid body")
    public void theClientSendsAPutRequestWithInvalidBody(String endpoint) {
        RestAssured.authentication = RestAssured.basic("admin", "snakeeyes");
        SerenityRest.given()
                .contentType(JSON)
                .auth().preemptive().basic("admin", "snakeeyes")
                .body("{ \"invalid\": \"data\" }")
                .when()
                .put(endpoint);
    }

    @Then("the response status should be {int} Bad Request")
    public void theResponseStatusShouldBeBadRequest(int statusCode) {
        SerenityRest.then().statusCode(statusCode);
    }


}
