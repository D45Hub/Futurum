package com.futurumgame.base.factories.ores;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.factories.Mine;
import com.futurumgame.base.factories.basic.GravelPit;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.advanced.Stone;
import com.futurumgame.base.resources.advanced.Wood;
import com.futurumgame.base.resources.basic.Water;
import com.futurumgame.base.resources.ores.TinOre;

import java.util.LinkedList;
import java.util.Queue;

public final class TinMine extends Mine<TinOre> {

    public static final String Name = "Tin Mine";

    private TinMine() {
        super(TinMine.class.getSimpleName(), TinOre.factory(), new Units(1, 2));
    }

    @Override
    public TinOre work() {
        return null;
    }

    @Override
    public Queue<Integer> getLongestResourceTreePath() {
        LinkedList<Integer> longestResourceTreePath = new LinkedList<>();
        longestResourceTreePath.add(Water.ID);
        longestResourceTreePath.add(Wood.ID);
        longestResourceTreePath.add(Stone.ID);
        longestResourceTreePath.add(TinOre.ID);
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
    protected TinOre instanceResourceWithAmount(Units amount) {
        return ResourceHelper.setToAmount(TinOre.factory(), amount);
    }

    public static TinMine factory() {
        return new TinMine();
    }

    public static TinMine factory(int level) {
        TinMine factory = factory();
        factory.levelTo(level);
        return factory;
    }
}
