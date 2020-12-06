package com.futurumgame.base.resources.basic;

import com.futurumgame.base.resources.BaseResource;

public final class Dirt extends BaseResource {

    public static final int ID = 1;

    private Dirt() {
        super(ID, Dirt.class.getSimpleName());
    }

    public static Dirt factory(){
        return new Dirt();
    }
}
