package com.futurumgame.base.resources.basic;

import com.futurumgame.base.resources.BaseResource;

public final class Dirt extends BaseResource {

    private Dirt() {
        super(1, Dirt.class.getSimpleName());
    }

    public static Dirt factory(){
        return new Dirt();
    }
}
