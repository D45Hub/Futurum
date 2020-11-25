package com.futurumgame.base.resources.ores;

import com.futurumgame.base.resources.Ore;

public class DiamondOre extends Ore {

    private DiamondOre() {
        super(26, DiamondOre.class.getSimpleName());
    }

    public static DiamondOre factory() {
        return new DiamondOre();
    }
}
