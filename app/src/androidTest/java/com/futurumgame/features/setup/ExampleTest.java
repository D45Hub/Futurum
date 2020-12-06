package com.futurumgame.features.setup;

import android.util.Log;

import com.futurumgame.base.MainActivity;
import com.futurumgame.base.ui.activities.FactoryManagerViewActivity;
import com.futurumgame.TestSuite;
import com.futurumgame.base.ui.activities.ResourceViewActivity;
import com.mauriciotogneri.greencoffee.GreenCoffeeConfig;
import com.mauriciotogneri.greencoffee.GreenCoffeeTest;
import com.mauriciotogneri.greencoffee.ScenarioConfig;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import androidx.test.rule.ActivityTestRule;
import gherkin.cli.Main;

@RunWith(Parameterized.class)
public class ExampleTest extends GreenCoffeeTest
{
    @Rule
    public ActivityTestRule<ResourceViewActivity> activity = new ActivityTestRule<>(ResourceViewActivity.class);

    public ExampleTest(ScenarioConfig scenarioConfig)
    {
        super(scenarioConfig);
    }

    @Parameterized.Parameters(name = "{0}")
    public static Iterable<ScenarioConfig> scenarios() throws IOException
    {

        return new GreenCoffeeConfig()
                .withFeatureFromUrl("https://raw.githubusercontent.com/D45Hub/Futurum/dev/app/src/androidTest/java/com/futurumgame/features/assets/upgradeFeature.feature")
                .takeScreenshotOnFail()
                .scenarios(TestSuite.ENGLISH);
    }

    @Test
    public void test()
    {
        start(new ExampleStepDefinition());
    }
}
