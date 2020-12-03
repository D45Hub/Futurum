package com.futurumgame.base.resources.advanced;

import com.futurumgame.base.resources.AdvancedResource;
import com.futurumgame.base.resources.Resource;

public final class Wood extends AdvancedResource {

    private Wood() {
        super(6, Wood.class.getSimpleName());
    }

    public static Wood factory() {
        return new Wood();
    }
}
