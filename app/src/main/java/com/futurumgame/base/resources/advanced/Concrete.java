package com.futurumgame.base.resources.advanced;

import com.futurumgame.base.resources.AdvancedResource;
import com.futurumgame.base.resources.ores.IronOre;

public class Concrete extends AdvancedResource {

    private Concrete() {
        super(22, Concrete.class.getSimpleName());
    }

    public static Concrete factory(){
        return new Concrete();
    }
}
