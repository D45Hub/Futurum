package com.futurumgame.base.resources.metals;

import com.futurumgame.base.resources.Metal;

public class Aluminium extends Metal {

    private Aluminium() {
        super(30, Aluminium.class.getSimpleName());
    }

    public static Aluminium factory(){
        return new Aluminium();
    }
}
