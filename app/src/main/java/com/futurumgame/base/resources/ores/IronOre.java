package com.futurumgame.base.resources.ores;

import com.futurumgame.base.resources.Ore;
import com.futurumgame.base.resources.advanced.Cement;

public class IronOre extends Ore {

    private IronOre() {
        super(20, IronOre.class.getSimpleName());
    }

    public static IronOre factory(){
        return new IronOre();
    }
}
