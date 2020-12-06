package com.futurumgame.base.resources.advanced;

import com.futurumgame.base.resources.AdvancedResource;

public class Stone extends AdvancedResource {

    public static final int ID = 9;

    private Stone() {
        super(ID, Stone.class.getSimpleName());
    }

    public static Stone factory() {
        return new Stone();
    }
}
