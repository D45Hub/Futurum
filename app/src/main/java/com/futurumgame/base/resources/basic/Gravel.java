package com.futurumgame.base.resources.basic;

import com.futurumgame.base.resources.BaseResource;

public class Gravel extends BaseResource {

    public static final int ID = 5;

    private Gravel() {
        super(ID, Gravel.class.getSimpleName());
    }

    public static Gravel factory(){
        return new Gravel();
    }
}
