package com.futurumgame.base.factories.basic;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.factories.BasicFactory;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.basic.Gravel;
import com.futurumgame.base.resources.basic.Sand;

import java.util.LinkedList;

public final class SandDune extends BasicFactory<Sand> {

    public static final String Name = "SandDune";

    private SandDune() {
        super(Name, Sand.factory(), new Units(1, 2));
    }

    @Override
    public Sand work() {
        Units baseProd = new Units(getLevel() * 7, -3 + Math.floor(Math.log(getLevel())/Math.log(5)));
        baseProd.multiply(new Units(1.44, Math.floor(Math.log10(getLevel()*32)/9)));
        return ResourceHelper.setToAmount(Sand.factory(), baseProd);
    }

    @Override
    public LinkedList<Resource> getUpgradeCosts() {
        LinkedList<Resource> costs = new LinkedList<>();
        double exponent = Math.floor(Math.log(getLevel()*4.2069*Math.sqrt(getLevel())));
        costs.add(ResourceHelper.setToAmount(Sand.factory(), new Units(getLevel() * 170, exponent)));
        return costs;
    }

    @Override
    protected void upgrade() {
        super.upgrade();
        getCapacity().multiply(new Units( 13, Math.sqrt(getLevel() * 0.33)));
    }

    @Override
    protected Sand instanceResourceWithAmount(Units amount) {
        return ResourceHelper.setToAmount(Sand.factory(), amount);
    }

    public static SandDune factory() {
        return new SandDune();
    }

    public static SandDune factory(int level) {
        SandDune factory = factory();
        factory.levelTo(level);
        return factory;
    }
}
