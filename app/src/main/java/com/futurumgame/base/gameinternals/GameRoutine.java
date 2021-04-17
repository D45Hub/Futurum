package com.futurumgame.base.gameinternals;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;

import com.futurumgame.base.MainActivity;
import com.futurumgame.base.R;
import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.enums.TimeUnits;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.ui.activities.UpdatableViewActivity;
import com.futurumgame.base.unlockables.UnlockableCollection;

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
    private final FactorySystem factories;
    private final WareHouse wareHouse;
    private final Hashtable<Integer, Units> measuredDeltas = new Hashtable<>();
    private final UnlockableCollection unlockables = new UnlockableCollection();

    private long tickRate = TimeUnits.Millisecond.inThisUnit(25);
    private Timer timer = new Timer(true);

    public GameRoutine(MainActivity mainActivity, @Nullable byte[] factorySystemData, @Nullable byte[] unlockableDatas, @Nullable byte[] wareHouseData) {
        main = mainActivity;
        currentActivity = main;
        current = this;
        factories = main.findViewById(R.id.FactorySystem);
        unlockables.unlock(unlockableDatas);
        factories.createSystem(factorySystemData);
        wareHouse = WareHouse.from(wareHouseData);
    }

    public void start() {
        schedule();
    }

    private void schedule() {
        timer.scheduleAtFixedRate(new Tick(factories, wareHouse), 0, tickRate);
        timer.scheduleAtFixedRate(new MeasureTick(wareHouse, measuredDeltas), 0, TimeUnits.Second.getTimeInMilliseconds());
    }

    private <T extends Resource> void add(Factory<T> factory) {
        factories.add(factory);
    }

    private void changeTickRate(long milliseconds) {
        tickRate = TimeUnits.Millisecond.inThisUnit(milliseconds);
        timer.cancel();
        timer = new Timer();
        schedule();
    }

    public static void emitSignalToMainThread(Runnable runnable) {
        handler.post(runnable);
    }

    public static void setTickRate(long milliseconds) {
        current.changeTickRate(milliseconds);
    }

    public static long getTickRate() {
        return current.tickRate;
    }

    public static MainActivity getMainActivity() {
        return current.main;
    }

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    public static WareHouse getWareHouse() {
        return current.wareHouse;
    }

    public static UnlockableCollection getUnlockables() {
        return current.unlockables;
    }

    public static FactorySystem getFactorySystem() {
        return current.factories;
    }

    public static Units getMeasuredProduction(int resourceID) {
        return current.measuredDeltas.get(resourceID);
    }

    public static void setNewCurrent(UpdatableViewActivity current) {
        previousActiveActivities.push(currentActivity);
        currentActivity = current;
    }

    public static void fallBackToPrevious() {
        currentActivity.finish();
        currentActivity = previousActiveActivities.pop();
    }

    public static <T extends Resource> void addNewFactory(Factory<T> factory) {
        current.add(factory);
    }

    public static void stop() {
        current.timer.cancel();
    }

    public static void updateUi() {
        currentActivity.updateUi(current.wareHouse);
    }
}
