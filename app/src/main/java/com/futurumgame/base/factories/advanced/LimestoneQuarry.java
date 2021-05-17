package com.futurumgame.base.factories.advanced;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.factories.Facility;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.factories.basic.GravelPit;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.advanced.Limestone;
import com.futurumgame.base.resources.advanced.Stone;
import com.futurumgame.base.resources.advanced.Wood;
import com.futurumgame.base.resources.basic.Water;

import java.util.LinkedList;
import java.util.Queue;

public final class LimestoneQuarry extends Facility<Limestone> {

    public static final String Name = "Limestone Quarry";

    private LimestoneQuarry() {
        super(Name, Limestone.factory(), new Units(1, 2));
    }

    @Override
    public Limestone work() {
        Units baseProd = new Units(getLevel() * 4, -2 + Math.floor(Math.log(getLevel() * Math.PI) / Math.log(2 * Math.PI)));
        baseProd.multiply(new Units(7.3, Math.floor(Math.log10(getLevel() / 7.3))));
        return ResourceHelper.setToAmount(Limestone.factory(), baseProd);
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
        double exponent = Math.floor(Math.log(Math.pow(getLevel() * 69, 0.69420 * Math.log10(getLevel() / 9.0))));
        costs.add(ResourceHelper.setToAmount(Wood.factory(), new Units(getLevel() * 51, exponent * 1.7)));
        costs.add(ResourceHelper.setToAmount(Stone.factory(), new Units(getLevel() * 113, exponent * 1.73)));
        return costs;
    }

    @Override
    protected LinkedList<Resource> requiredResources() {
        LinkedList<Resource> requieredResources = new LinkedList<>();
        Units baseValue = new Units(1, Math.floor(5.5 * Math.log(getLevel() + 13)) + 1);
        requieredResources.add(ResourceHelper.setToAmount(Wood.factory(), baseValue.copy()));
        baseValue.divide(new Units(4.5, 0));
        requieredResources.add(ResourceHelper.setToAmount(Stone.factory(), baseValue.copy()));
        return requieredResources;
    }

    @Override
    protected void upgrade() {
        super.upgrade();
        getCapacity().multiply(new Units(8.8, Math.sqrt(getLevel() * 0.79)));
    }

    @Override
    protected Limestone instanceResourceWithAmount(Units amount) {
        return ResourceHelper.setToAmount(Limestone.factory(), amount);
    }

    public static LimestoneQuarry factory() {
        return new LimestoneQuarry();
    }

    public static LimestoneQuarry factory(int level) {
        LimestoneQuarry factory = factory();
        factory.levelTo(level);
        return factory;
    }
}
