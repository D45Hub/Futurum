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
