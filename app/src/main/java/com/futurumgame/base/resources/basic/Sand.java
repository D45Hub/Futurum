package com.futurumgame.base.resources.basic;

import com.futurumgame.base.resources.BaseResource;

public final class Sand extends BaseResource {

    public static final int ID = 3;

    private Sand() {
        super(ID, Sand.class.getSimpleName());
    }

    public static Sand factory(){
        return new Sand();
    }
}
