package com.futurumgame.base.resources.basic;

import com.futurumgame.base.resources.BaseResource;

public class Oil extends BaseResource {

    public static final int ID = 6;

    private Oil() {
        super(ID, Oil.class.getSimpleName());
    }

    public static Oil factory(){
        return new Oil();
    }
}
