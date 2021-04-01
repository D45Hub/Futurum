package com.futurumgame.base.resources.advanced;

import com.futurumgame.base.resources.AdvancedResource;

public class Limestone extends AdvancedResource {

    public static final int ID = 10;

    private Limestone() {
        super(ID, Limestone.class.getSimpleName());
    }

    public static Limestone factory() {
        return new Limestone();
    }
}
