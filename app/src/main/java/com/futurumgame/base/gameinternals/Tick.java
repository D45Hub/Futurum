package com.futurumgame.base.gameinternals;

import android.util.Log;

import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.threading.ThreadHelper;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TimerTask;
import java.util.concurrent.FutureTask;

public class Tick extends TimerTask {

    private final Queue<FutureTask<? extends Resource>> tasks = new LinkedList<>();
    private final FactorySystem factories;
    private final WareHouse wareHouse;

    public Tick(FactorySystem factories, WareHouse wareHouse) {
        this.factories = factories;
        this.wareHouse = wareHouse;
    }

    @Override
    public void run() {
        long current = System.currentTimeMillis();
        factories.forEach(this::onCall);
        ThreadHelper.whenAll(tasks, wareHouse::addToResources);
        GameRoutine.emitSignalToMainThread(wareHouse::updateUi);
    }

    private void onCall(FactoryNode<? extends Resource> node) {
        FutureTask<? extends Resource> t;
        if(node.getCurrent().gatherRequiredResources(wareHouse)) {
            t = new FutureTask<>(node.getCurrent()::work);
            tasks.add(t);
        }else {
            int fallBack = 1;
            while (node.canFallBack(fallBack)) {
                Factory<? extends Resource> fallBackFactory = node.fallBack(fallBack, wareHouse);
                if (fallBackFactory != null) {
                    t = new FutureTask<>(fallBackFactory::work);
                    tasks.add(t);
                    break;
                }
                fallBack++;
            }
        }
    }
}
