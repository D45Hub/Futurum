package com.futurumgame.base.gameinternals;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;

import com.futurumgame.base.MainActivity;
import com.futurumgame.base.R;
import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.enums.DataFile;
import com.futurumgame.base.enums.TimeUnits;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.ui.activities.UpdatableViewActivity;
import com.futurumgame.base.unlockables.UnlockableCollection;
import com.futurumgame.base.util.Logger;

import java.util.Arrays;
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
    private final WareHouse wareHouse = new WareHouse();
    private final Hashtable<Integer, Units> measuredDeltas = new Hashtable<>();
    private final UnlockableCollection resourceUnlockables = UnlockableCollection.createResourceUnlockables();

    private boolean hardReset;
    private long tickRate = TimeUnits.Millisecond.inThisUnit(25);
    private Timer timer = new Timer(true);

    public GameRoutine(MainActivity mainActivity) {
        main = mainActivity;
        currentActivity = main;
        current = this;
        factories = main.findViewById(R.id.FactorySystem);
    }

    public void initFromFiles() {
        resourceUnlockables.from(DataFile.ResourceUnlockables.read());
        factories.from(DataFile.FactorySystem.read());
        wareHouse.from(DataFile.WareHouse.read());
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

    public static UnlockableCollection getResourceUnlockables() {
        return current.resourceUnlockables;
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

    public static boolean hardResetActivated() {
        return current.hardReset;
    }

    public static void requestHardReset() {
        current.hardReset = true;
        Activity context = current.main;
        Logger.e(GameRoutine.class, "request hard reset");
        Arrays.stream(DataFile.values()).forEach(DataFile::clear);
        Intent restartIntent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName() );
        @SuppressLint("WrongConstant")
        PendingIntent intent = PendingIntent.getActivity(
                context, 0,
                restartIntent, Intent.FLAG_ACTIVITY_CLEAR_TOP);
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC, System.currentTimeMillis() + 100, intent);
        context.finish();
    }

    public static void stop() {
        current.timer.cancel();
    }

    public static void updateUi() {
        currentActivity.updateUi(current.wareHouse);
    }
}
