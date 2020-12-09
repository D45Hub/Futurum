package com.futurumgame.base.resources.ores;

import com.futurumgame.base.resources.Ore;

public class DiamondOre extends Ore {

    public static final int ID = 27;

    private DiamondOre() {
        super(ID, DiamondOre.class.getSimpleName());
    }

    public static DiamondOre factory() {
        return new DiamondOre();
    }
}
