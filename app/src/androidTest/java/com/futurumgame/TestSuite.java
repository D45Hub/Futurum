package com.futurumgame;

import android.os.Bundle;

import com.futurumgame.base.MainActivity;
import com.futurumgame.base.ui.activities.FactoryManagerViewActivity;
import com.futurumgame.features.setup.ExampleTest;
import com.mauriciotogneri.greencoffee.GreenCoffeeConfig;
import com.mauriciotogneri.greencoffee.GreenCoffeeTest;
import com.mauriciotogneri.greencoffee.ScenarioConfig;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Suite;

import java.io.IOException;
import java.util.Locale;

import androidx.test.rule.ActivityTestRule;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ExampleTest.class
})
public class TestSuite
{
    public static final Locale ENGLISH = new Locale("en", "GB");
}
