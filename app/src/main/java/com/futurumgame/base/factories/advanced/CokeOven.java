package com.futurumgame.base.factories.advanced;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.factories.Facility;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.advanced.Coke;
import com.futurumgame.base.resources.advanced.Mortar;
import com.futurumgame.base.resources.advanced.Stone;
import com.futurumgame.base.resources.advanced.Wood;
import com.futurumgame.base.resources.basic.Clay;
import com.futurumgame.base.resources.basic.Water;
import com.futurumgame.base.resources.ores.Coal;
import com.futurumgame.base.resources.ores.CopperOre;

import java.util.LinkedList;
import java.util.Queue;

public final class CokeOven extends Facility<Coke> {

    public static final String Name = "Coke Oven";

    public CokeOven() {
        super(Name, Coke.factory(), new Units(5, 2));
    }

    @Override
    public Coke work() {
        Units baseProd = new Units(getLevel() * 4, -3 + Math.floor(Math.log10(Math.pow(getLevel(), Math.E))));
        baseProd.multiply(new Units(1.5, Math.floor(Math.log10(Math.pow(getLevel(), 13.1) / 5717.0))));
        return ResourceHelper.setToAmount(Coke.factory(), baseProd);
    }

    @Override
    public Queue<Integer> getLongestResourceTreePath() {
        LinkedList<Integer> longestResourceTreePath = new LinkedList<>();
        longestResourceTreePath.add(Water.ID);
        longestResourceTreePath.add(Wood.ID);
        longestResourceTreePath.add(Stone.ID);
        longestResourceTreePath.add(CopperOre.ID);
        longestResourceTreePath.add(Coal.ID);
        return longestResourceTreePath;
    }

    @Override
    public LinkedList<Resource> getUpgradeCosts() {
        LinkedList<Resource> costs = new LinkedList<>();
        double exponent = Math.floor(Math.log(Math.pow(getLevel() * 69, 0.69420 * Math.log10(getLevel() / 9.0))));
        costs.add(ResourceHelper.setToAmount(Mortar.factory(), new Units(getLevel() * 51, exponent * 1.66)));
        costs.add(ResourceHelper.setToAmount(Stone.factory(), new Units(getLevel() * 113, exponent * 1.83)));
        costs.add(ResourceHelper.setToAmount(Clay.factory(), new Units(getLevel() * 13, exponent * 1.53)));
        return costs;
    }

    @Override
    protected LinkedList<Resource> requiredResources() {
        LinkedList<Resource> costs = new LinkedList<>();
        costs.add(ResourceHelper.setToAmount(Coal.factory(), new Units(getLevel(), Math.floor(Math.log(getLevel()) * 1.7 + 1))));
        return costs;
    }

    @Override
    protected Coke instanceResourceWithAmount(Units amount) {
        return ResourceHelper.setToAmount(Coke.factory(), amount);
    }
}
