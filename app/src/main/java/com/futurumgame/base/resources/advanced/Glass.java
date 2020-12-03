package com.futurumgame.base.resources.advanced;

import com.futurumgame.base.resources.AdvancedResource;

public class Glass extends AdvancedResource {

    private Glass() {
        super(17, Glass.class.getSimpleName());
    }

    public static Glass factory() {
        return new Glass();
    }
}
