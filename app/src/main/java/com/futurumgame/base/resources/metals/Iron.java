package com.futurumgame.base.resources.metals;

import com.futurumgame.base.resources.Metal;
import com.futurumgame.base.resources.ores.IronOre;

public class Iron extends Metal {

    private Iron() {
        super(20, Iron.class.getSimpleName());
    }

    public static Iron factory(){
        return new Iron();
    }
}
