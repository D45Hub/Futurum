package com.futurumgame.base.gameinternals;

import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.resources.Resource;

import java.util.Stack;

public class FactoryNode<T extends Resource> {

    private final Stack<Factory<T>> fallbackFactories = new Stack<>();
    private Factory<T> current;

    public FactoryNode(Factory<T> current) {
        this.current = current;
    }

    public Factory<T> getCurrent() {
        return current;
    }

    public String getResourceName() {
        return current.getResource().getName();
    }

    public void changePrimaryFactory(Factory<? extends Resource> factory) {
        fallbackFactories.push(current);
        //noinspection unchecked
        current = (Factory<T>) factory;
    }

    public boolean canFallBack(int fallBack) {
        return fallBack > fallbackFactories.size();
    }

    public Factory<? extends Resource> fallBack(int fallBack, WareHouse wareHouse) {
        Factory<? extends  Resource> fallBackFactory = fallbackFactories.get(fallBack-1);
        if(fallBackFactory.gatherRequiredResources(wareHouse)){
            return fallBackFactory;
        }
        return null;
    }
}
