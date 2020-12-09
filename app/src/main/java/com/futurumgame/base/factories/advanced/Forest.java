package com.futurumgame.base.factories.advanced;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.factories.Facility;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.advanced.Wood;
import com.futurumgame.base.resources.basic.Water;

import java.util.LinkedList;
import java.util.Queue;

public final class Forest extends Facility<Wood> {

    private Forest() {
        super(Forest.class.getSimpleName(), Wood.factory(), new Units(1, 2));
    }

    @Override
    public Wood work() {
        return null;
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
        return null;
    }

    @Override
    protected LinkedList<Resource> requiredResources() {
        return null;
    }

    @Override
    protected void upgrade() {
        super.upgrade();
    }

    @Override
    protected Wood instanceResourceWithAmount(Units amount) {
        return ResourceHelper.setToAmount(Wood.factory(), amount);
    }

    public static Forest factory() {
        return new Forest();
    }
}
