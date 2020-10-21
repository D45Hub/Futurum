package com.futurumgame.base.gameinternals;

import android.os.Handler;
import android.os.Looper;

import com.futurumgame.base.MainActivity;
import com.futurumgame.base.factories.basic.WaterMill;

import java.util.Timer;

public class GameRoutine {

    private static GameRoutine current;
    private static final Handler handler = new Handler(Looper.getMainLooper());

    private final FactorySystem factories = new FactorySystem();
    private final WareHouse wareHouse;

    private Timer timer = new Timer(true);
    private long tickRate = 25;

    public GameRoutine(MainActivity mainActivity) {
        wareHouse = new WareHouse(mainActivity);
        factories.add(WaterMill.factory());
        current = this;
    }

    public WareHouse getWareHouse() {
        return wareHouse;
    }

    public void start() {
        schedule();
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
}
