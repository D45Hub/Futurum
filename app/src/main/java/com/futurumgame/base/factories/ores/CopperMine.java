package com.futurumgame.base.factories.ores;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.factories.Mine;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.advanced.Stone;
import com.futurumgame.base.resources.advanced.Wood;
import com.futurumgame.base.resources.basic.Water;
import com.futurumgame.base.resources.ores.CopperOre;
import com.futurumgame.base.resources.ores.TinOre;

import java.util.LinkedList;
import java.util.Queue;

public final class CopperMine extends Mine<CopperOre> {

    private CopperMine() {
        super(CopperMine.class.getSimpleName(), CopperOre.factory(), new Units(1, 2));
    }

    @Override
    public CopperOre work() {
        return null;
    }

    @Override
    public Queue<Integer> getLongestResourceTreePath() {
        LinkedList<Integer> longestResourceTreePath = new LinkedList<>();
        longestResourceTreePath.add(Water.ID);
        longestResourceTreePath.add(Wood.ID);
        longestResourceTreePath.add(Stone.ID);
        longestResourceTreePath.add(CopperOre.ID);
        return longestResourceTreePath;
    }

    @Override
    public LinkedList<Resource> getUpgradeCosts() {
        return null;
    }

    @Override
    protected LinkedList<Resource> requiredResources() {
        LinkedList<Resource> requiredResources = new LinkedList<>();
        requiredResources.add(Stone.factory());
        return requiredResources;
    }

    @Override
    protected void upgrade() {
        super.upgrade();
    }

    @Override
    protected CopperOre instanceResourceWithAmount(Units amount) {
        return ResourceHelper.setToAmount(CopperOre.factory(), amount);
    }

    public static CopperMine factory() {
        return new CopperMine();
    }
}
