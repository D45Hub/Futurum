package com.futurumgame.features.setup;

import android.os.Bundle;

import androidx.test.runner.MonitoringInstrumentation;
import cucumber.api.CucumberOptions;
import cucumber.api.android.CucumberInstrumentationCore;


/*@CucumberOptions(
        features = "features",
        glue = "com.wordpress.commonplayground.test")*/
public class Instrumentation extends MonitoringInstrumentation {
    private final CucumberInstrumentationCore instrumentationCore = new CucumberInstrumentationCore(this);
    @Override
    public void onCreate(Bundle arguments) {
        super.onCreate(arguments);

        instrumentationCore.create(arguments);
        start();
    }

    @Override
    public void onStart() {
        super.onStart();
        waitForIdleSync();
        instrumentationCore.start();
    }
}
