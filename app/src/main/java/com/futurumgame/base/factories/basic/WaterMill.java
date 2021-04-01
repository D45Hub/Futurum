package com.futurumgame.base.factories.basic;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.factories.BasicFactory;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.basic.Water;

import java.util.LinkedList;
import java.util.Queue;

public final class WaterMill extends BasicFactory<Water> {

    protected WaterMill() {
        super(WaterMill.class.getSimpleName(), Water.factory(), new Units(1, 2));
    }

    @Override
    public Water work() {
        Units baseProd = new Units(getLevel() * 4, -2 + Math.floor(Math.log(getLevel())/Math.log(5)));
        baseProd.multiply(new Units(1.5, Math.floor(Math.log10(getLevel()))));
        Water produced = Water.factory();
        produced.setCount(baseProd);
        return produced;
    }

    @Override
    public LinkedList<Resource> getUpgradeCosts() {
        LinkedList<Resource> costs = new LinkedList<>();
        costs.add(ResourceHelper.setToAmount(Water.factory(),
                new Units(getLevel() * 5, Math.floor(Math.log10(getLevel()*7)*0.3))));
        return costs;
    }

    @Override
    protected void upgrade() {
        super.upgrade();
        getStorage().multiply(new Units(getLevel(), Math.floor(Math.log(getLevel()))));
    }

    @Override
    protected Water instanceResourceWithAmount(Units amount) {
        return ResourceHelper.setToAmount(Water.factory(), amount);
    }

    public static WaterMill factory() {
        return new WaterMill();
    }
}