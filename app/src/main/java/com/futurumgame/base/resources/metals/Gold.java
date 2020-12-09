package com.futurumgame.base.resources.metals;

import com.futurumgame.base.resources.Metal;
import com.futurumgame.base.resources.ores.GoldOre;

public class Gold extends Metal {

    public static final int ID = 29;

    private Gold() {
        super(ID, Gold.class.getSimpleName());
    }

    public static Gold factory(){
        return new Gold();
    }
}
