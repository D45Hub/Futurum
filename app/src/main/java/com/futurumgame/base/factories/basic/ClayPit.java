package com.futurumgame.base.factories.basic;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.factories.BasicFactory;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.basic.Clay;
import com.futurumgame.base.resources.basic.Dirt;
import com.futurumgame.base.resources.basic.Water;

import java.util.LinkedList;

public final class ClayPit extends BasicFactory<Clay> {

    public static final String Name = "Clay Pit";

    private ClayPit() {
        super(Name, Clay.factory(), new Units(1, 2));
    }

    @Override
    public Clay work() {
        Units baseProd = new Units(getLevel() * 4, -2 + Math.floor(Math.log(getLevel())/Math.log(5)));
        baseProd.multiply(new Units(1.5, Math.floor(Math.log10(getLevel()))));
        return ResourceHelper.setToAmount(Clay.factory(), baseProd);
    }

    @Override
    public LinkedList<Resource> getUpgradeCosts() {
        LinkedList<Resource> costs = new LinkedList<>();
        double exponent = Math.floor(Math.log(Math.pow(getLevel(), 4.33*Math.log10(getLevel()))));
        costs.add(ResourceHelper.setToAmount(Water.factory(), new Units(getLevel() * 100, exponent)));
        return costs;
    }

    @Override
    protected void upgrade() {
        super.upgrade();
        getCapacity().multiply(new Units(10, Math.sqrt(getLevel() * 0.25)));
    }

    @Override
    protected Clay instanceResourceWithAmount(Units amount) {
        return ResourceHelper.setToAmount(Clay.factory(), amount);
    }

    public static ClayPit factory() {
        return new ClayPit();
    }

    public static ClayPit factory(int level) {
        ClayPit factory = factory();
        factory.levelTo(level);
        return factory;
    }
}
