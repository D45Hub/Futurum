package com.futurumgame.base.resources.ores;

import com.futurumgame.base.resources.Ore;
import com.futurumgame.base.resources.advanced.Concrete;

public class GoldOre extends Ore {

    private GoldOre() {
        super(23, GoldOre.class.getSimpleName());
    }

    public static GoldOre factory(){
        return new GoldOre();
    }
}
