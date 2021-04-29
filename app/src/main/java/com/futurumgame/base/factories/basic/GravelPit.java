package com.futurumgame.base.factories.basic;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.factories.BasicFactory;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.basic.Clay;
import com.futurumgame.base.resources.basic.Dirt;
import com.futurumgame.base.resources.basic.Gravel;
import com.futurumgame.base.resources.basic.Oil;

import java.util.LinkedList;

public final class GravelPit extends BasicFactory<Gravel> {

    public static final String Name = "Gravel Pit";

    private GravelPit() {
        super(Name, Gravel.factory(), new Units(1, 2));
    }

    @Override
    public Gravel work() {
        Units baseProd = new Units(getLevel() * 7, -3 + Math.floor(Math.log(getLevel())/Math.log(5)));
        baseProd.multiply(new Units(1.44, Math.floor(Math.log10(getLevel()*32)/9)));
        return ResourceHelper.setToAmount(Gravel.factory(), baseProd);
    }

    @Override
    public LinkedList<Resource> getUpgradeCosts() {
        LinkedList<Resource> costs = new LinkedList<>();
        double exponent = Math.floor(Math.log(getLevel()*4.2069*Math.sqrt(getLevel())));
        costs.add(ResourceHelper.setToAmount(Gravel.factory(), new Units(getLevel() * 170, exponent)));
        return costs;
    }

    @Override
    protected void upgrade() {
        super.upgrade();
        getCapacity().multiply(new Units( 13, Math.sqrt(getLevel() * 0.33)));
    }

    @Override
    protected Gravel instanceResourceWithAmount(Units amount) {
        return ResourceHelper.setToAmount(Gravel.factory(), amount);
    }

    public static GravelPit factory() {
        return new GravelPit();
    }

    public static GravelPit factory(int level) {
        GravelPit factory = factory();
        factory.levelTo(level);
        return factory;
    }
}
