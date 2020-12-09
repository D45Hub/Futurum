package com.futurumgame.base.factories;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.enums.FactoryFormatter;
import com.futurumgame.base.enums.UpgradeResult;
import com.futurumgame.base.gameinternals.WareHouse;
import com.futurumgame.base.resources.Resource;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Factory<T extends Resource> {

    private static final int LevelCap = 1000000000;

    private final String name;
    private final T resource;

    private boolean automated = false;
    private int level = 1;
    private Units internalStorage = Units.Zero.copy();
    private Units internalCapacity;

    protected Factory(String name, T resource, Units internalCapacity) {
        this.name = name;
        this.internalCapacity = internalCapacity;
        this.resource = resource;
    }

    public final String getName(){
        return name;
    }

    public final T getResource() {
        return resource;
    }

    public final boolean isAutomated() {
        return automated;
    }

    public final int getLevel() {
        return level;
    }

    public final Units getCapacity(){
        return internalCapacity;
    }

    public final Units getStorage(){
        return internalStorage;
    }

    public final void workManually() {
        T resource = work();
        Units multiplier = new Units(5, Math.floor(1/Math.log10((level+100)/169.0 +1)-3));
        resource.getCount().multiply(multiplier);
        addToStorage(resource.getCount());
    }

    public final boolean gatherRequiredResources(WareHouse wareHouse) {
        if (!isAutomated()) {
            return false;
        }
        if (resource.isBaseResource()) {
            return true;
        }
        if (wareHouse.canOfferResources(requiredResources())) {
            wareHouse.offerResources(requiredResources());
            return true;
        }
        return false;
    }

    public final boolean canUpgrade(WareHouse wareHouse) {
        return wareHouse.canOfferResources(getUpgradeCosts());
    }

    public final UpgradeResult tryUpgrade(WareHouse wareHouse) {
        LinkedList<Resource> upgradeCosts = getUpgradeCosts();
        if (wareHouse.canOfferResources(upgradeCosts)) {
            wareHouse.offerResources(upgradeCosts);
            upgrade();
            return UpgradeResult.Successful;
        }
        return UpgradeResult.Failure;
    }

    public final T emptyStorage() {
        Units current = internalStorage.copy();
        internalStorage.setValue(Units.Zero.getValue());
        internalStorage.setScale(Units.Zero.getScale());
        return instanceResourceWithAmount(current);
    }

    protected final void addToStorage(Units units) {
        internalStorage.add(units);
        if (internalStorage.isBiggerThan(internalCapacity)) {
            internalStorage.setValue(internalCapacity.getValue());
            internalStorage.setScale(internalCapacity.getScale());
        }
    }

    public abstract T work();

    public abstract Queue<Integer> getLongestResourceTreePath();

    public abstract LinkedList<Resource> getUpgradeCosts();

    protected abstract LinkedList<Resource> requiredResources();

    protected abstract T instanceResourceWithAmount(Units amount);

    protected void upgrade() {
        increaseLevel();
    }

    private void increaseLevel(){
        level++;
    }

    @Override
    public final String toString() {
        return FactoryFormatter.Debug.format(this);
    }
}