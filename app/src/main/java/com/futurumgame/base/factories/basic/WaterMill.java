package com.futurumgame.base.factories.basic;

import android.content.res.Resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.enums.UpgradeResult;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.gameinternals.WareHouse;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.resources.basic.Water;

import java.util.LinkedList;
import java.util.Queue;

public class WaterMill extends Factory<Water> {

    protected WaterMill() {
        super(WaterMill.class.getSimpleName(), Water.factory(), new Units(1, 2));
    }

    @Override
    public Water work() {
        Units baseProd = new Units(getLevel() * 4, -4);
        baseProd.multiply(new Units(1.5, Math.floor(Math.log10(getLevel()))));
        Water produced = Water.factory();
        produced.setCount(baseProd);
        return produced;
    }

    @Override
    public Queue<String> getLongestResourceTreePath() {
        return new LinkedList<>();
    }

    @Override
    protected LinkedList<Resource> requiredResources() {
        return new LinkedList<>();
    }

    @Override
    public LinkedList<Resource> getUpgradeCosts() {
        LinkedList<Resource> costs = new LinkedList<>();
        Resource cost = Water.factory();
        cost.setCount(new Units(getLevel()*5, getLevel()-1));
        costs.add(cost);
        return costs;
    }

    public static WaterMill factory() {
        return new WaterMill();
    }
}
