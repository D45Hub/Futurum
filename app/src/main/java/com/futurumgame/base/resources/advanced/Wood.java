package com.futurumgame.base.resources.advanced;

import com.futurumgame.base.resources.AdvancedResource;
import com.futurumgame.base.resources.Resource;

public final class Wood extends AdvancedResource {

    public static final int ID = 7;

    private Wood() {
        super(ID, Wood.class.getSimpleName());
    }

    public static Wood factory() {
        return new Wood();
    }
}
