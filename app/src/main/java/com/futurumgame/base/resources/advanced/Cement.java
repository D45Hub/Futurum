package com.futurumgame.base.resources.advanced;

import com.futurumgame.base.resources.AdvancedResource;
import com.futurumgame.base.resources.basic.Sand;

public class Cement extends AdvancedResource {

    private Cement() {
        super(19, Cement.class.getSimpleName());
    }

    public static Cement factory(){
        return new Cement();
    }
}
