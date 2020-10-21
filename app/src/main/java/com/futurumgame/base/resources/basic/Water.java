package com.futurumgame.base.resources.basic;

import com.futurumgame.base.resources.Resource;

public class Water extends Resource {

    private Water() {
        super(0, Water.class.getSimpleName());
    }

    public static Water factory(){
        return new Water();
    }

    @Override
    public boolean isBaseResource() {
        return true;
    }
}
