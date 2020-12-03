package com.futurumgame.base.resources.metals;

import com.futurumgame.base.resources.Metal;
import com.futurumgame.base.resources.ores.GoldOre;

public class Gold extends Metal {

    private Gold() {
        super(28, Gold.class.getSimpleName());
    }

    public static Gold factory(){
        return new Gold();
    }
}
