package com.futurumgame.base.resources.basic;

import com.futurumgame.base.resources.BaseResource;

public final class Sand extends BaseResource {

    private Sand() {
        super(3, Sand.class.getSimpleName());
    }

    public static Sand factory(){
        return new Sand();
    }
}
