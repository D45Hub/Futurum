package com.futurumgame.base.resources.ores;

import com.futurumgame.base.resources.Ore;
import com.futurumgame.base.resources.advanced.Limestone;

public class TinOre extends Ore {

    private TinOre() {
        super(10, TinOre.class.getSimpleName());
    }

    public static TinOre factory() {
        return new TinOre();
    }
}
