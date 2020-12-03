package com.futurumgame.base.gameinternals;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.futurumgame.base.MainActivity;
import com.futurumgame.base.R;
import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.enums.TimeUnits;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.factories.basic.WaterMill;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.ui.activities.UpdatableViewActivity;

import java.util.Hashtable;
import java.util.Stack;
import java.util.Timer;

public class GameRoutine {

    private static final long Second = 1000;

    private static GameRoutine current;
    private static UpdatableViewActivity currentActivity;
    private static final Handler handler = new Handler(Looper.getMainLooper());
    private static final Stack<UpdatableViewActivity> previousActiveActivities = new Stack<>();

    private final MainActivity main;
    private final WareHouse wareHouse;
    private final FactorySystem factories;
    private final Hashtable<Integer, Units> measuredDeltas = new Hashtable<>();

    private long tickRate = TimeUnits.Millisecond.inThisUnit(25);
    private Timer timer = new Timer(true);

    public GameRoutine(MainActivity mainActivity) {
        main = mainActivity;
        currentActivity = main;
        current = this;
        factories = main.findViewById(R.id.FactorySystem);
        wareHouse = new WareHouse(main);
        add(WaterMill.factory());
    }

    public WareHouse getWareHouse() {
        return wareHouse;
    }

    public void start() {
        schedule();
    }

    public <T extends Resource> void add(Factory<T> factory){
        factories.add(factory);
    }

    private void schedule() {
        timer.scheduleAtFixedRate(new Tick(factories, wareHouse), 0, tickRate);
        timer.scheduleAtFixedRate(new MeasureTick(wareHouse, measuredDeltas), 0, TimeUnits.Second.getTimeInMilliseconds());
    }

    private void changeTickRate(long milliseconds) {
        tickRate = TimeUnits.Millisecond.inThisUnit(milliseconds);
        timer.cancel();
        timer = new Timer();
        schedule();
    }

    public static void emitSignalToMainThread(Runnable runnable){
        handler.post(runnable);
    }

    public static void setTickRate(long milliseconds){
        current.changeTickRate(milliseconds);
    }

    public static long getTickRate(){
        return current.tickRate;
    }

    public static MainActivity getMainActivity(){
        return current.main;
    }

    public static WareHouse getCurrentWareHouse(){
        return current.wareHouse;
    }

    public static Activity getCurrentActivity(){
        return currentActivity;
    }

    public static Units getMeasuredProduction(int resourceID) {
        return current.measuredDeltas.get(resourceID);
    }

    public static void setNewCurrent(UpdatableViewActivity current){
        previousActiveActivities.push(currentActivity);
        currentActivity = current;
    }

    public static void fallBackToPrevious() {
        currentActivity.finish();
        currentActivity = previousActiveActivities.pop();
    }

    public static void stop() {
        current.timer.cancel();
    }

    public static void updateUi() {
        currentActivity.updateUi(getCurrentWareHouse());
    }
}
