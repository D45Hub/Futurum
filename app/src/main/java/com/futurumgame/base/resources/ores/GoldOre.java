package com.futurumgame.base.resources.ores;

import com.futurumgame.base.resources.Ore;
import com.futurumgame.base.resources.advanced.Concrete;

public class GoldOre extends Ore {

    public static final int ID = 24;

    private GoldOre() {
        super(ID, GoldOre.class.getSimpleName());
    }

    public static GoldOre factory(){
        return new GoldOre();
    }
}
