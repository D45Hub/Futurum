package com.futurumgame.base.resources.metals;

import com.futurumgame.base.resources.Metal;

public class Aluminium extends Metal {

    public static final int ID = 31;

    private Aluminium() {
        super(ID, Aluminium.class.getSimpleName());
    }

    public static Aluminium factory(){
        return new Aluminium();
    }
}
