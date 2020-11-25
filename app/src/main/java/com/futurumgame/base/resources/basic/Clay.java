package com.futurumgame.base.resources.basic;

import com.futurumgame.base.resources.BaseResource;

public class Clay extends BaseResource {

    private Clay() {
        super(2, Clay.class.getSimpleName());
    }

    public static Clay factory(){
        return new Clay();
    }
}
