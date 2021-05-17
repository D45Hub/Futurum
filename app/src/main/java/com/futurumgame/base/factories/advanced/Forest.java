package com.futurumgame.base.factories.advanced;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.factories.Facility;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.advanced.Mortar;
import com.futurumgame.base.resources.advanced.Stone;
import com.futurumgame.base.resources.advanced.Wood;
import com.futurumgame.base.resources.basic.Dirt;
import com.futurumgame.base.resources.basic.Sand;
import com.futurumgame.base.resources.basic.Water;

import java.util.LinkedList;
import java.util.Queue;

public final class Forest extends Facility<Wood> {

    private Forest() {
        super(Forest.class.getSimpleName(), Wood.factory(), new Units(1, 2));
    }

    @Override
    public Wood work() {
        Units baseProd = new Units(getLevel() * 4, -2 + Math.floor(Math.log(getLevel()) / Math.log(7)));
        baseProd.multiply(new Units(1.7, Math.floor(Math.log(getLevel() / 3))));
        return ResourceHelper.setToAmount(Wood.factory(), baseProd);
    }

    @Override
    public Queue<Integer> getLongestResourceTreePath() {
        LinkedList<Integer> longestResourceTreePath = new LinkedList<>();
        longestResourceTreePath.add(Water.ID);
        longestResourceTreePath.add(Wood.ID);
        return longestResourceTreePath;
    }

    @Override
    public LinkedList<Resource> getUpgradeCosts() {
        LinkedList<Resource> costs = new LinkedList<>();
        double exponent = Math.floor(Math.log(Math.pow(getLevel() * 420, 0.069420 * Math.log10(getLevel() / 9.0))));
        costs.add(ResourceHelper.setToAmount(Stone.factory(), new Units(getLevel() * 53, exponent * 1.23)));
        costs.add(ResourceHelper.setToAmount(Wood.factory(), new Units(getLevel() * 133, exponent * 1.3)));
        return costs;
    }

    @Override
    protected LinkedList<Resource> requiredResources() {
        LinkedList<Resource> requieredResources = new LinkedList<>();
        Units baseValue = new Units(6, Math.floor(Math.log(getLevel() + 13)));
        requieredResources.add(ResourceHelper.setToAmount(Water.factory(), baseValue.copy()));
        baseValue.multiply(new Units(3,0));
        requieredResources.add(ResourceHelper.setToAmount(Dirt.factory(), baseValue.copy()));
        requieredResources.add(ResourceHelper.setToAmount(Sand.factory(), baseValue.copy()));
        return requieredResources;
    }

    @Override
    protected void upgrade() {
        super.upgrade();
        getCapacity().multiply(new Units(9, Math.sqrt(getLevel() * 0.55)));
    }

    @Override
    protected Wood instanceResourceWithAmount(Units amount) {
        return ResourceHelper.setToAmount(Wood.factory(), amount);
    }

    public static Forest factory() {
        return new Forest();
    }
}
