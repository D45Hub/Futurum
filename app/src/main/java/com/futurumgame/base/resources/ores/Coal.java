package com.futurumgame.base.resources.ores;

import com.futurumgame.base.resources.Ore;

public class Coal extends Ore {

    public static final int ID = 13;

    private Coal() {
        super(ID, Coal.class.getSimpleName());
    }

    public static Coal factory() {
        return new Coal();
    }
}
