package com.futurumgame.base.resources.advanced;

import com.futurumgame.base.resources.AdvancedResource;
import com.futurumgame.base.resources.basic.Sand;

public class Cement extends AdvancedResource {

    public static final int ID = 20;

    private Cement() {
        super(ID, Cement.class.getSimpleName());
    }

    public static Cement factory(){
        return new Cement();
    }
}
