package com.futurumgame.base.resources.advanced;

import com.futurumgame.base.resources.AdvancedResource;

public class Stone extends AdvancedResource {

    private Stone() {
        super(8, Stone.class.getSimpleName());
    }

    public static Stone factory() {
        return new Stone();
    }
}
