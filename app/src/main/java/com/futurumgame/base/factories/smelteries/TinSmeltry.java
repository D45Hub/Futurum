package com.futurumgame.base.factories.smelteries;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.factories.Smeltery;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.advanced.Stone;
import com.futurumgame.base.resources.advanced.Wood;
import com.futurumgame.base.resources.basic.Water;
import com.futurumgame.base.resources.metals.Tin;
import com.futurumgame.base.resources.ores.Coal;
import com.futurumgame.base.resources.ores.TinOre;

import java.util.LinkedList;
import java.util.Queue;

public final class TinSmeltry extends Smeltery<Tin, Coal> {

    public static final String Name = "Tin Smeltry";

    private TinSmeltry() {
        super(Name, Tin.factory(), ResourceHelper.setToAmount(Coal.factory(), Units.Ten.copy()), new Units(1, 2));
    }

    @Override
    protected LinkedList<Resource> requiredSmeltingComponents() {
        LinkedList<Resource> requiredSmeltingComponents = new LinkedList<>();
        requiredSmeltingComponents.add(ResourceHelper.setToAmount(Tin.factory(), Units.Ten.copy()));
        return requiredSmeltingComponents;
    }

    @Override
    public Tin work() {
        return null;
    }

    @Override
    public Queue<Integer> getLongestResourceTreePath() {
        LinkedList<Integer> longestResourceTreePath = new LinkedList<>();
        longestResourceTreePath.add(Water.ID);
        longestResourceTreePath.add(Wood.ID);
        longestResourceTreePath.add(Stone.ID);
        longestResourceTreePath.add(TinOre.ID);
        longestResourceTreePath.add(Tin.ID);
        return longestResourceTreePath;
    }

    @Override
    public LinkedList<Resource> getUpgradeCosts() {
        return null;
    }

    @Override
    protected void upgrade() {
        super.upgrade();
    }

    @Override
    protected Tin instanceResourceWithAmount(Units amount) {
        return ResourceHelper.setToAmount(Tin.factory(), amount);
    }

    public static TinSmeltry factory() {
        return new TinSmeltry();
    }

    public static TinSmeltry factory(int level) {
        TinSmeltry factory = factory();
        factory.levelTo(level);
        return factory;
    }
}
