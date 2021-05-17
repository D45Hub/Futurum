package com.futurumgame.base.factories.advanced;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.factories.Facility;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.factories.basic.GravelPit;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.advanced.Mortar;
import com.futurumgame.base.resources.advanced.Stone;
import com.futurumgame.base.resources.advanced.Wood;
import com.futurumgame.base.resources.basic.Clay;
import com.futurumgame.base.resources.basic.Oil;
import com.futurumgame.base.resources.basic.Water;

import java.util.LinkedList;
import java.util.Queue;

public final class MortarMixer extends Facility<Mortar> {

    public static String Name = "Mortar Mixer";

    private MortarMixer() {
        super(Name, Mortar.factory(), new Units(1, 2));
    }

    @Override
    public Mortar work() {
        Units baseProd = new Units(getLevel() * 2, -2 + Math.floor(Math.log(getLevel())/Math.log(3)));
        baseProd.multiply(new Units(3, Math.floor(Math.log10(Math.pow(getLevel()/5, 1.5)))));
        return ResourceHelper.setToAmount(Mortar.factory(), baseProd);
    }

    @Override
    public Queue<Integer> getLongestResourceTreePath() {
        LinkedList<Integer> longestResourceTreePath = new LinkedList<>();
        longestResourceTreePath.add(Water.ID);
        longestResourceTreePath.add(Mortar.ID);
        return longestResourceTreePath;
    }

    @Override
    public LinkedList<Resource> getUpgradeCosts() {
        LinkedList<Resource> requieredResources = new LinkedList<>();
        Units baseValue = new Units(7.5, Math.floor(3.5*Math.log(getLevel() + 13)));
        requieredResources.add(ResourceHelper.setToAmount(Wood.factory(), baseValue.copy()));
        baseValue.divide(new Units(4.5,1));
        requieredResources.add(ResourceHelper.setToAmount(Stone.factory(), baseValue.copy()));
        return requieredResources;
    }

    @Override
    protected LinkedList<Resource> requiredResources() {
        return null;
    }

    @Override
    protected void upgrade() {
        super.upgrade();
        getCapacity().multiply(new Units(8, Math.sqrt(getLevel() * 0.5)));
    }

    @Override
    protected Mortar instanceResourceWithAmount(Units amount) {
        return ResourceHelper.setToAmount(Mortar.factory(), amount);
    }

    public static MortarMixer factory() {
        return new MortarMixer();
    }

    public static MortarMixer factory(int level) {
        MortarMixer factory = factory();
        factory.levelTo(level);
        return factory;
    }
}
