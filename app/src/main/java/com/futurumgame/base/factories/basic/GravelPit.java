package com.futurumgame.base.factories.basic;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.factories.BasicFactory;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.basic.Clay;
import com.futurumgame.base.resources.basic.Dirt;
import com.futurumgame.base.resources.basic.Gravel;

import java.util.LinkedList;

public final class GravelPit extends BasicFactory<Gravel> {

    private GravelPit() {
        super(GravelPit.class.getSimpleName(), Gravel.factory(), new Units(1, 2));
    }

    @Override
    public Gravel work() {
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
    protected Gravel instanceResourceWithAmount(Units amount) {
        return ResourceHelper.setToAmount(Gravel.factory(), amount);
    }

    public static GravelPit factory() {
        return new GravelPit();
    }
}
