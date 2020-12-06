package com.futurumgame.base.resources.basic;

import com.futurumgame.base.resources.BaseResource;

public class Clay extends BaseResource {

    public static final int ID = 2;

    private Clay() {
        super(ID, Clay.class.getSimpleName());
    }

    public static Clay factory(){
        return new Clay();
    }
}
