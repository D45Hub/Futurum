package com.futurumgame.base.factories.basic;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.factories.BasicFactory;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.basic.Dirt;
import com.futurumgame.base.resources.basic.Water;

import java.util.LinkedList;

public final class DirtPit extends BasicFactory<Dirt> {

    public static final String Name = "Dirt Pit";

    private DirtPit() {
        super(Name, Dirt.factory(), new Units(1, 2));
    }

    @Override
    public Dirt work() {
        Units baseProd = new Units(getLevel() * 4, -2 + Math.floor(Math.log(getLevel())/Math.log(5)));
        baseProd.multiply(new Units(1.5, Math.floor(Math.log10(getLevel()))));
        return ResourceHelper.setToAmount(Dirt.factory(), baseProd);
    }

    @Override
    public LinkedList<Resource> getUpgradeCosts() {
        LinkedList<Resource> costs = new LinkedList<>();
        double exponent = Math.floor(Math.log10(Math.pow(getLevel(), 2.33)));
        costs.add(ResourceHelper.setToAmount(Water.factory(), new Units(getLevel() * 100, exponent)));
        exponent = Math.floor(Math.sqrt(Math.pow(getLevel(), 1.83)));
        double mantissa = Math.log(Math.pow(getLevel() * ((double) getLevel()), 0.69));
        costs.add(ResourceHelper.setToAmount(Dirt.factory(), new Units(mantissa, exponent)));
        return costs;
    }

    @Override
    protected void upgrade() {
        super.upgrade();
        getCapacity().multiply(new Units(10, Math.sqrt(getLevel() * 0.25)));
    }

    @Override
    protected Dirt instanceResourceWithAmount(Units amount) {
        return ResourceHelper.setToAmount(Dirt.factory(), amount);
    }

    public static DirtPit factory() {
        return new DirtPit();
    }

    public static DirtPit factory(int level) {
        DirtPit factory = factory();
        factory.levelTo(level);
        return factory;
    }
}
