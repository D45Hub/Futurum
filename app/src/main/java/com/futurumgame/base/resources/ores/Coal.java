package com.futurumgame.base.resources.ores;

import com.futurumgame.base.resources.Ore;

public class Coal extends Ore {

    private Coal() {
        super(12, Coal.class.getSimpleName());
    }

    public static Coal factory() {
        return new Coal();
    }
}
