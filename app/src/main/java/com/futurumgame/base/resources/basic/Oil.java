package com.futurumgame.base.resources.basic;

import com.futurumgame.base.resources.BaseResource;

public class Oil extends BaseResource {

    private Oil() {
        super(5, Oil.class.getSimpleName());
    }

    public static Oil factory(){
        return new Oil();
    }
}
