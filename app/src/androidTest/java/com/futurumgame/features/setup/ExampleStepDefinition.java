package com.futurumgame.features.setup;

import com.futurumgame.base.MainActivity;
import com.futurumgame.base.R;
import com.futurumgame.base.enums.UpgradeResult;
import com.futurumgame.base.gameinternals.GameRoutine;
import com.futurumgame.base.ui.adapter.ResourceAdapter;
import com.mauriciotogneri.greencoffee.GreenCoffeeSteps;
import com.mauriciotogneri.greencoffee.annotations.And;
import com.mauriciotogneri.greencoffee.annotations.Given;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.annotations.When;

import org.junit.Before;
import org.junit.BeforeClass;

import androidx.recyclerview.widget.LinearLayoutManager;

import static org.junit.Assert.assertEquals;


public class ExampleStepDefinition extends GreenCoffeeSteps {

    @Given("^the USER has enough resource$")
    public void checkForEnoughResources() {

    }


    @Given("^the USER has not enough resources$")
    public void checkForNotEnoughResources() {

        onViewWithObject(UpgradeResult.Successful).checkIfIsDisplayed();
    }

    @When("^the USER presses the Upgrade button$")
    public void upgradeButtonPress() {
        assertEquals(true, onViewWithId(R.id.upgradeButton).checkIfIsDisplayed());
        assertEquals(true, onViewWithId(R.id.upgradeButton).checkIfIsClickable());
        waitFor(2000);
        onViewWithId(R.id.upgradeButton).click();
    }

    @Then("^the USER gets notified that the upgrade failed$")
    public void failedUpgradeNotification() {
        //TODO test
        System.out.println("Meme");
    }

    @Then("^the amount of resources of USER are subtracted$")
    public void subtractedResources() {
        //TODO test
        System.out.println("Meme");
    }

    @And("^the USER get notified that the upgrade was successful$")
    public void forEachSessionShouldTheTitleTitleBeShown() {
        //TODO test
    }

}
