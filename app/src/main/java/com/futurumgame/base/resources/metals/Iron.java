package com.futurumgame.base.resources.metals;

import com.futurumgame.base.resources.Metal;
import com.futurumgame.base.resources.ores.IronOre;

public class Iron extends Metal {

    public static final int ID = 22;

    private Iron() {
        super(ID, Iron.class.getSimpleName());
    }

    public static Iron factory(){
        return new Iron();
    }
}
