package com.sas.automation.yahtzee.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.sas.automation.yahtzee.steps",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        monochrome = true
)
public class YahtzeeApiTestRunner {
}