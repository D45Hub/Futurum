package com.futurumgame.base.resources.ores;

import com.futurumgame.base.resources.Ore;
import com.futurumgame.base.resources.advanced.Cement;

public class IronOre extends Ore {

    public static final int ID = 21;

    private IronOre() {
        super(ID, IronOre.class.getSimpleName());
    }

    public static IronOre factory(){
        return new IronOre();
    }
}
