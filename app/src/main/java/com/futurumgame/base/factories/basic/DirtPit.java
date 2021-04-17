package com.futurumgame.base.factories.basic;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.factories.BasicFactory;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.basic.Dirt;

import java.util.LinkedList;

public final class DirtPit extends BasicFactory<Dirt> {

    public static final String Name = "Dirt Pit";

    private DirtPit() {
        super(Name, Dirt.factory(), new Units(1, 2));
    }

    @Override
    public Dirt work() {
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
