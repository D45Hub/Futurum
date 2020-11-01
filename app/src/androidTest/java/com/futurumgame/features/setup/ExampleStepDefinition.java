package com.futurumgame.features.setup;

import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@RunWith(AndroidJUnit4.class)
public class ExampleStepDefinition {


    @Before("@sessionoverview-feature")
    public void setup() {
    }

    @After("@sessionoverview-feature")
    public void tearDown() {
    }

    @When("^Activity Session Overview is open$")
    public void activitySessionOverviewIsOpen() {
    }

    @Then("^The page should list all the current active Sessions$")
    public void thePageShouldListAllTheCurrentActiveSessions() {
        //TODO test
    }

    @And("^For each Session should the title ([^\"]*) be shown$")
    public void forEachSessionShouldTheTitleTitleBeShown(String testTitle) {
        //TODO test
    }

}
