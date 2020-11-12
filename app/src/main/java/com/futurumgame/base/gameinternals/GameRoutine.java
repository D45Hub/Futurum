package com.futurumgame.base.gameinternals;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.futurumgame.base.MainActivity;
import com.futurumgame.base.R;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.factories.basic.WaterMill;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.ui.activities.FactoryManagerViewActivity;
import com.futurumgame.base.ui.activities.ResourceViewActivity;
import com.futurumgame.base.ui.activities.UpdatableViewActivity;

import java.util.Timer;

public class GameRoutine {

    private static GameRoutine current;
    private static UpdatableViewActivity currentActivity;
    private static final Handler handler = new Handler(Looper.getMainLooper());

    private final MainActivity main;
    private final WareHouse wareHouse;
    private final FactorySystem factories;

    private long tickRate = 25;
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
    }

    private void changeTickRate(long milliseconds) {
        tickRate = milliseconds;
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

    public static void setCurrent(UpdatableViewActivity current){
        GameRoutine.currentActivity = current;
    }

    public static void stop() {
        current.timer.cancel();
    }

    public static void updateUi() {
        currentActivity.updateUi(getCurrentWareHouse());
    }
}
