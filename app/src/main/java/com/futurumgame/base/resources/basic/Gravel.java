package com.futurumgame.base.resources.basic;

import com.futurumgame.base.resources.BaseResource;

public class Gravel extends BaseResource {

    private Gravel() {
        super(5, Gravel.class.getSimpleName());
    }

    public static Gravel factory(){
        return new Gravel();
    }
}
