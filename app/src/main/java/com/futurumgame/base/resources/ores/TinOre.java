package com.futurumgame.base.resources.ores;

import com.futurumgame.base.resources.Ore;
import com.futurumgame.base.resources.advanced.Limestone;

public class TinOre extends Ore {

    public static final int ID = 11;

    private TinOre() {
        super(ID, TinOre.class.getSimpleName());
    }

    public static TinOre factory() {
        return new TinOre();
    }
}
