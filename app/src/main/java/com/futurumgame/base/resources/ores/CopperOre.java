package com.futurumgame.base.resources.ores;

import com.futurumgame.base.resources.Ore;

public class CopperOre extends Ore {

    private CopperOre() {
        super(11, CopperOre.class.getSimpleName());
    }

    public static CopperOre factory() {
        return new CopperOre();
    }
}
