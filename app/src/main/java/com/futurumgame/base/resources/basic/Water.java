package com.futurumgame.base.resources.basic;

import com.futurumgame.base.resources.BaseResource;
import com.futurumgame.base.resources.Resource;

public final class Water extends BaseResource {

    public static final int ID = 0;

    private Water() {
        super(ID, Water.class.getSimpleName());
    }

    public static Water factory(){
        return new Water();
    }
}
