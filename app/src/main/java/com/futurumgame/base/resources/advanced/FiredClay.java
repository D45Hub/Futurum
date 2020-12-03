package com.futurumgame.base.resources.advanced;

import com.futurumgame.base.resources.AdvancedResource;

public class FiredClay extends AdvancedResource {

    private FiredClay() {
        super(16, FiredClay.class.getSimpleName());
    }

    public static FiredClay factory() {
        return new FiredClay();
    }
}
