package com.futurumgame.base.factories.basic;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.resources.basic.Water;

import java.util.LinkedList;
import java.util.Queue;

public class WaterMill extends Factory<Water> {

    protected WaterMill() {
        super(WaterMill.class.getSimpleName(), Water.factory(), new Units(1, 2));
    }

    @Override
    public Water work() {
        Units baseProd = new Units(getLevel() * 4, -2);
        baseProd.multiply(new Units(1.5, Math.floor(Math.log10(getLevel()))));
        addToStorage(baseProd);
        Water produced = Water.factory();
        produced.setCount(emptyStorage());
        return produced;
    }

    @Override
    public Queue<String> getLongestResourceTreePath() {
        return new LinkedList<>();
    }

    @Override
    protected LinkedList<Resource> requiredResources() {
        return new LinkedList<>();
    }

    public static WaterMill factory() {
        return new WaterMill();
    }
}
