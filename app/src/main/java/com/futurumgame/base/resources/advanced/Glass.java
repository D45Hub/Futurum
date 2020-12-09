package com.futurumgame.base.resources.advanced;

import com.futurumgame.base.resources.AdvancedResource;

public class Glass extends AdvancedResource {

    public static final int ID = 18;

    private Glass() {
        super(ID, Glass.class.getSimpleName());
    }

    public static Glass factory() {
        return new Glass();
    }
}
