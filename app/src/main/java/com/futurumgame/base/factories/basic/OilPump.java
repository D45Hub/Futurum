package com.futurumgame.base.factories.basic;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.factories.BasicFactory;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.basic.Clay;
import com.futurumgame.base.resources.basic.Gravel;
import com.futurumgame.base.resources.basic.Oil;
import com.futurumgame.base.resources.basic.Water;

import java.util.LinkedList;

public final class OilPump extends BasicFactory<Oil> {

    public static final String Name = "Oil Pump";

    private OilPump() {
        super(Name, Oil.factory(), new Units(1, 2));
    }

    @Override
    public Oil work() {
        Units baseProd = new Units(getLevel() * 4, -2 + Math.floor(Math.log(getLevel())/Math.log(7)));
        baseProd.multiply(new Units(1.5, Math.floor(Math.log10(getLevel()/5))));
        return ResourceHelper.setToAmount(Oil.factory(), baseProd);
    }

    @Override
    public LinkedList<Resource> getUpgradeCosts() {
        LinkedList<Resource> costs = new LinkedList<>();
        double exponent = Math.floor(Math.log(Math.pow(getLevel()*69, 4.2069*Math.log(getLevel()))));
        costs.add(ResourceHelper.setToAmount(Oil.factory(), new Units(getLevel() * 170, exponent)));
        return costs;
    }

    @Override
    protected void upgrade() {
        super.upgrade();
        getCapacity().multiply(new Units(10, Math.sqrt(getLevel() * 0.25)));
    }

    @Override
    protected Oil instanceResourceWithAmount(Units amount) {
        return ResourceHelper.setToAmount(Oil.factory(), amount);
    }

    public static OilPump factory() {
        return new OilPump();
    }

    public static OilPump factory(int level) {
        OilPump factory = factory();
        factory.levelTo(level);
        return factory;
    }
}
