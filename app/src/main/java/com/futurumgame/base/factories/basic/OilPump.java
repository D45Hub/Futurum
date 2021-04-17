package com.futurumgame.base.factories.basic;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.factories.BasicFactory;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.basic.Gravel;
import com.futurumgame.base.resources.basic.Oil;

import java.util.LinkedList;

public final class OilPump extends BasicFactory<Oil> {

    public static final String Name = "Oil Pump";

    private OilPump() {
        super(Name, Oil.factory(), new Units(1, 2));
    }

    @Override
    public Oil work() {
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
