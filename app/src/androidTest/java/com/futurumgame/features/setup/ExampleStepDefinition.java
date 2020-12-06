package com.futurumgame.features.setup;

import android.content.Intent;
import android.view.View;

import com.futurumgame.base.MainActivity;
import com.futurumgame.base.R;
import com.futurumgame.base.enums.MetaData;
import com.futurumgame.base.enums.UpgradeResult;
import com.futurumgame.base.factories.basic.WaterMill;
import com.futurumgame.base.gameinternals.FactoryNode;
import com.futurumgame.base.gameinternals.GameRoutine;
import com.futurumgame.base.resources.basic.Water;
import com.futurumgame.base.ui.activities.ResourceViewActivity;
import com.futurumgame.base.ui.adapter.ResourceAdapter;
import com.mauriciotogneri.greencoffee.GreenCoffeeSteps;
import com.mauriciotogneri.greencoffee.annotations.And;
import com.mauriciotogneri.greencoffee.annotations.Given;
import com.mauriciotogneri.greencoffee.annotations.Then;
import com.mauriciotogneri.greencoffee.annotations.When;
import com.mauriciotogneri.greencoffee.interactions.ActionableObject;

import org.junit.Before;
import org.junit.BeforeClass;

import androidx.recyclerview.widget.LinearLayoutManager;

import static org.junit.Assert.assertEquals;


public class ExampleStepDefinition extends GreenCoffeeSteps {

    private ActionableObject oldLabel;

    @Given("^the USER is on the Resource View$")
    public void checkForEnoughResources() {
        onViewWithText("WATER").click();
        waitFor(2000);
        assertEquals(true, onViewWithId(R.id.clickButton).checkIfIsDisplayed());
    }

    @When("^the USER taps on the Button$")
    public void tapOnButton() {
        oldLabel = onViewWithId(R.id.FactoryOveriew);
        waitFor(2000);
        onViewWithId(R.id.clickButton).click();
        waitFor(2000);
    }

    @Then("^calculate the generated amount of resources$")
    public void updateThingy() {}


    @And("^add the amount of resources to USER$")
    public void addResources()
    {
    }

    @And("^update the label with the amount of resources$")
    public void updateLabel()
    {
        assertEquals(false, oldLabel.equals(onViewWithId(R.id.FactoryOveriew)));
    }


}
