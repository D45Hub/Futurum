package com.futurumgame.base.resources.advanced;

import com.futurumgame.base.resources.AdvancedResource;

public class FiredClay extends AdvancedResource {

    public static final int ID = 17;

    private FiredClay() {
        super(ID, FiredClay.class.getSimpleName());
    }

    public static FiredClay factory() {
        return new FiredClay();
    }
}
