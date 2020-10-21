

package com.futurumgame.base.factories;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.gameinternals.WareHouse;
import com.futurumgame.base.resources.Resource;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Factory<T extends Resource> {

    private static final int LevelCap = 1000000000;

    private final String name;
    private final T resource;

    private int level = 1;
    private Units internalStorage = Units.Zero.copy();
    private Units internalCapacity;

    protected Factory(String name, T resource, Units internalCapacity) {
        this.name = name;
        this.internalCapacity = internalCapacity;
        this.resource = resource;
    }

    public final T getResource() {
        return resource;
    }

    protected final int getLevel(){
        return level;
    }

    public abstract T work();

    public abstract Queue<String> getLongestResourceTreePath();

    protected abstract LinkedList<Resource> requiredResources();

    public final boolean gatherRequiredResources(WareHouse wareHouse) {
        if(resource.isBaseResource()){
            return true;
        }
        if (wareHouse.canOfferResources(requiredResources())) {
            wareHouse.offerResources(requiredResources());
            return true;
        }
        return false;
    }

    protected final void addToStorage(Units units) {
        internalStorage.add(units);
        if(internalStorage.isBiggerThan(internalCapacity)){
            internalStorage.setValue(internalCapacity.getValue());
            internalStorage.setScale(internalCapacity.getScale());
        }
    }

    protected final Units emptyStorage(){
        Units current = internalStorage.copy();
        internalStorage.setValue(Units.Zero.getValue());
        internalStorage.setScale(Units.Zero.getScale());
        return current;
    }

    @Override
    public String toString() {
        return String.format("%s Level: %d Capacity: %s Storage: %s", name, level, internalCapacity, internalStorage);
    }
}
