package com.futurumgame.base.resources.ores;

import com.futurumgame.base.resources.Ore;

public class SilverOre extends Ore {

    private SilverOre() {
        super(24, SilverOre.class.getSimpleName());
    }

    public static SilverOre factory(){
        return new SilverOre();
    }
}
