package com.futurumgame.base.resources.ores;

import com.futurumgame.base.resources.Ore;

public class CopperOre extends Ore {

    public static final int ID = 12;

    private CopperOre() {
        super(ID, CopperOre.class.getSimpleName());
    }

    public static CopperOre factory() {
        return new CopperOre();
    }
}
