package com.futurumgame.base.resources.advanced;

import com.futurumgame.base.resources.AdvancedResource;

public class Limestone extends AdvancedResource {

    private Limestone() {
        super(9, Limestone.class.getSimpleName());
    }

    public static Limestone factory() {
        return new Limestone();
    }
}
