package com.futurumgame.base.resources.advanced;

import com.futurumgame.base.resources.AdvancedResource;
import com.futurumgame.base.resources.ores.IronOre;

public class Concrete extends AdvancedResource {

    public static final int ID = 23;

    private Concrete() {
        super(ID, Concrete.class.getSimpleName());
    }

    public static Concrete factory(){
        return new Concrete();
    }
}
