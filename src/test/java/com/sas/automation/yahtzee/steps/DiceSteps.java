package com.sas.automation.yahtzee.steps;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static net.serenitybdd.rest.SerenityRest.then;
import static org.hamcrest.Matchers.*;


public class DiceSteps {

    private Response postResponse;
    private Response getResponse;



    @Then("the response body should contain a list of five dice")
    public void theResponseBodyShouldContainAListOfFiveDice() {
        then().body("data", hasSize(5));
    }

    @Then("each die should have a valid value between {int} and {int}")
    public void eachDieShouldHaveAValidValueBetween(int minValue, int maxValue) {
        for (int i = 0; i < 5; i++) {
            then().body("data[" + i + "].value", greaterThanOrEqualTo(minValue))
                    .and().body("data[" + i + "].value", lessThanOrEqualTo(maxValue));
        }
    }

    @When("the client sends a POST All Dice request to {string}")
    public void theClientSendsAPostRequestTo(String endpoint) {
        postResponse = given().when().post(endpoint);
    }

    @When("the client sends a GET All Dice request to {string}")
    public void theClientSendsAGetRequestTo(String endpoint) {
        getResponse = given().when().get(endpoint);
    }

    @Then("the dice values should match the rolled dice")
    public void theDiceValuesShouldMatchTheRolledDice() {
        List<Map<String, Object>> rolledDice = postResponse.jsonPath().getList("data");
        List<Map<String, Object>> fetchedDice = getResponse.jsonPath().getList("data");

        for (int i = 0; i < 5; i++) {
            int rolledValue = (int) rolledDice.get(i).get("value");
            int fetchedValue = (int) fetchedDice.get(i).get("value");

            assert rolledValue == fetchedValue : "Die values do not match!";
        }
    }






}

