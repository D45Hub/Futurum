package com.futurumgame.base.resources.basic;

import com.futurumgame.base.resources.Resource;

public class Wood extends Resource {

    public Wood(){
        super(1, Wood.class.getSimpleName());
    }

    public static Wood factory(){
        return new Wood();
    }

    @Override
    public boolean isBaseResource() {
        return true;
    }
}
