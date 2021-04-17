package com.futurumgame.base.factories.basic;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.factories.BasicFactory;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.basic.Clay;
import com.futurumgame.base.resources.basic.Dirt;

import java.util.LinkedList;

public final class ClayPit extends BasicFactory<Clay> {

    public static final String Name = "Clay Pit";

    private ClayPit() {
        super(Name, Clay.factory(), new Units(1, 2));
    }

    @Override
    public Clay work() {
        return null;
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
