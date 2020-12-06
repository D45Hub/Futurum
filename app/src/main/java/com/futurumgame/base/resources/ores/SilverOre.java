package com.futurumgame.base.resources.ores;

import com.futurumgame.base.resources.Ore;

public class SilverOre extends Ore {

    public static final int ID = 25;

    private SilverOre() {
        super(ID, SilverOre.class.getSimpleName());
    }

    public static SilverOre factory(){
        return new SilverOre();
    }
}
