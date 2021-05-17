package com.futurumgame.base.factories.advanced;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.factories.Facility;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.factories.basic.GravelPit;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.advanced.Stone;
import com.futurumgame.base.resources.advanced.Wood;
import com.futurumgame.base.resources.basic.Dirt;
import com.futurumgame.base.resources.basic.Water;

import java.util.LinkedList;
import java.util.Queue;

public final class StoneQuarry extends Facility<Stone> {

    public static final String Name = "Stone Quarry";

    private StoneQuarry() {
        super(Name, Stone.factory(), new Units(1, 2));
    }

    @Override
    public Stone work() {
        Units baseProd = new Units(getLevel() * 4, -2 + Math.floor(Math.log(getLevel()) / Math.log(7)));
        baseProd.multiply(new Units(1.5, Math.floor(Math.log10(getLevel() / 5))));
        return ResourceHelper.setToAmount(Stone.factory(), baseProd);
    }

    @Override
    public Queue<Integer> getLongestResourceTreePath() {
        LinkedList<Integer> longestResourceTreePath = new LinkedList<>();
        longestResourceTreePath.add(Water.ID);
        longestResourceTreePath.add(Wood.ID);
        longestResourceTreePath.add(Stone.ID);
        return longestResourceTreePath;
    }

    @Override
    public LinkedList<Resource> getUpgradeCosts() {
        LinkedList<Resource> costs = new LinkedList<>();
        double exponent = Math.floor(Math.log(Math.pow(getLevel() * 420, 0.069420 * Math.log10(getLevel() / 9.0))));
        costs.add(ResourceHelper.setToAmount(Wood.factory(), new Units(getLevel() * 513, exponent * 1.23)));
        costs.add(ResourceHelper.setToAmount(Stone.factory(), new Units(getLevel() * 1313, exponent * 1.3)));
        return costs;
    }

    @Override
    protected LinkedList<Resource> requiredResources() {
        LinkedList<Resource> requieredResources = new LinkedList<>();
        Units baseValue = new Units(7.5, Math.floor(3.5*Math.log(getLevel() + 13)));
        requieredResources.add(ResourceHelper.setToAmount(Wood.factory(), baseValue.copy()));
        baseValue.divide(new Units(4.5,1));
        requieredResources.add(ResourceHelper.setToAmount(Stone.factory(), baseValue.copy()));
        return requieredResources;
    }

    @Override
    protected void upgrade() {
        super.upgrade();
        getCapacity().multiply(new Units(3, Math.sqrt(getLevel() * 0.73)));
    }

    @Override
    protected Stone instanceResourceWithAmount(Units amount) {
        return ResourceHelper.setToAmount(Stone.factory(), amount);
    }

    public static StoneQuarry factory() {
        return new StoneQuarry();
    }

    public static StoneQuarry factory(int level) {
        StoneQuarry factory = factory();
        factory.levelTo(level);
        return factory;
    }
}
