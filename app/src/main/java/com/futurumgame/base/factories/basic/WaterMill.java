package com.futurumgame.base.factories.basic;

import android.text.Editable;
import android.text.Spannable;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.factories.BasicFactory;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.basic.Water;

import java.util.LinkedList;
import java.util.Queue;

public final class WaterMill extends BasicFactory<Water> {

    public static final String Name = "Watermill";

    private WaterMill() {
        super(Name, Water.factory(), new Units(1, 2));
    }

    @Override
    public Water work() {
        Units baseProd = new Units(getLevel() * 4, -2 + Math.floor(Math.log(getLevel())/Math.log(5)));
        baseProd.multiply(new Units(1.5, Math.floor(Math.log10(getLevel()))));
        return ResourceHelper.setToAmount(Water.factory(), baseProd);
    }

    @Override
    public LinkedList<Resource> getUpgradeCosts() {
        LinkedList<Resource> costs = new LinkedList<>();
        costs.add(ResourceHelper.setToAmount(Water.factory(),
                new Units(getLevel() * 5, Math.floor(Math.log10(getLevel()*7)*0.3))));
        return costs;
    }

    @Override
    protected void upgrade() {
        super.upgrade();
        getCapacity().multiply(new Units(getLevel(), Math.floor(Math.log(getLevel()))));
    }

    @Override
    protected Water instanceResourceWithAmount(Units amount) {
        return ResourceHelper.setToAmount(Water.factory(), amount);
    }

    public static WaterMill factory() {
        return new WaterMill();
    }

    public static WaterMill factory(int level) {
        WaterMill factory = factory();
        factory.levelTo(level);
        return factory;
    }
}