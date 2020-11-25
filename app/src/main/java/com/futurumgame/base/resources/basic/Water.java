package com.futurumgame.base.resources.basic;

import com.futurumgame.base.resources.BaseResource;
import com.futurumgame.base.resources.Resource;

public final class Water extends BaseResource {

    private Water() {
        super(0, Water.class.getSimpleName());
    }

    public static Water factory(){
        return new Water();
    }
}
