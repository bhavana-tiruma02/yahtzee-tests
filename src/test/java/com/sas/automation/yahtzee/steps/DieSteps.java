package com.sas.automation.yahtzee.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.Matchers.*;

public class DieSteps {

    private Response response;


    @When("the client sends a GET request to {string} with Accept header {string}")
    public void theClientSendsAGETRequestToWithAcceptHeader(String endpoint, String acceptHeader) {
        response = SerenityRest.given()
                .header("Accept", acceptHeader)
                .when()
                .get(endpoint);
    }


    @And("the response body should contain the die value in {string} format")
    public void theResponseBodyShouldContainTheDieValueInFormat(String responseType) {
        switch (responseType) {
            case "json":
                response.then().body("data.value", isA(Number.class));
                break;
            case "float":
                response.then().body("data.value", matchesPattern("\\d+\\.\\d"));
                break;
            case "word":
                response.then().body("data.value", anyOf(equalTo("one"), equalTo("two"), equalTo("three"),
                        equalTo("four"), equalTo("five"), equalTo("six")));
                break;
            case "dots":
                response.then().body("data.value", matchesPattern("\\.+"));
                break;
        }
    }

    @Then("the response body should contain an error message")
    public void theResponseBodyShouldContainAnErrorMessage() {
        SerenityRest.then().body("status", equalTo("failed"));
    }

    @When("the client sends a PUT request to {string} with a valid die ID and value")
    public void theClientSendsAPUTRequestToWithAValidDieIDAndValue(String endpoint) {
        String body = "{\"id\": 1, \"value\": 5}";
        SerenityRest.given().auth().preemptive().basic("admin", "snakeeyes")
                .contentType("application/json")
                .body(body)
                .when().put(endpoint);
    }

    @When("the client sends a PUT request to {string} with invalid Auth")
    public void theClientSendsAPUTRequestToWithInvalidAuth(String endpoint) {
        String body = "{\"id\": 1, \"value\": 5}";
        SerenityRest.given().auth().preemptive().basic("admin", "dummy")
                .contentType("application/json")
                .body(body)
                .when().put(endpoint);
    }

    @When("the client sends a PUT request to {string} with an invalid die ID of {int}")
    public void theClientSendsAPUTRequestToWithAnInvalidDieID(String endpoint, int dieId) {
        String body = "{\"id\": " + dieId + ", \"value\": 5}";
        SerenityRest.given().auth().preemptive().basic("admin", "snakeeyes")
                .contentType("application/json")
                .body(body)
                .when().put(endpoint);
    }

    @When("the client sends a PUT request to {string} with an invalid die value of {int}")
    public void theClientSendsAPUTRequestToWithAnInvalidDieValue(String endpoint, int dieValue) {
        String body = "{\"id\": 1, \"value\": " + dieValue + "}";
        SerenityRest.given().auth().preemptive().basic("admin", "snakeeyes")
                .contentType("application/json")
                .body(body)
                .when().put(endpoint);
    }

    @When("the client sends a PUT request to {string} with an invalid request body")
    public void theClientSendsAPUTRequestToWithAnInvalidRequestBody(String endpoint) {
        String invalidBody = "{\"invalidField\": \"invalidData\"}";
        SerenityRest.given().auth().preemptive().basic("admin", "snakeeyes")
                .contentType("application/json")
                .body(invalidBody)
                .when().put(endpoint);
    }


    @Then("the response body should contain the error message {string}")
    public void theResponseBodyShouldContainAnErrorMessage(String errorMessage) {
        SerenityRest.then().body("status", equalTo("failed"))
                .and().body("data", equalTo(errorMessage));
    }

    @When("the client sends a POST request to {string}")
    public void theClientSendsAPOSTRequestTo(String endpoint) {
        given().when().post(endpoint);
    }

    @Then("the response body should contain the new die value for die {int}")
    public void theResponseBodyShouldContainTheNewDieValueForDie(int dieId) {
        then().body("data.id", equalTo(dieId))
                .and().body("data.value", greaterThanOrEqualTo(1))
                .and().body("data.value", lessThanOrEqualTo(6));
    }

    @Then("the response should have a status of {string}")
    public void theResponseShouldHaveAStatusOf(String status) {
        then().body("status", equalTo(status));
    }



}
