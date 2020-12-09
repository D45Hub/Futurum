package com.futurumgame.base.resources;

import com.futurumgame.base.additionalDatatypes.Units;

public class ResourceHelper {

    private ResourceHelper(){
    }

    public static<T extends Resource> T setToAmount(T resource, Units units){
        resource.setCount(units);
        return resource;
    }
}
