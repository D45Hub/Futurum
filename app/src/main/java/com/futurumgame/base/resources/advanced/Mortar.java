package com.futurumgame.base.resources.advanced;

import com.futurumgame.base.resources.AdvancedResource;

public class Mortar extends AdvancedResource {

    public static final int ID = 8;

    private Mortar() {
        super(ID, Mortar.class.getSimpleName());
    }

    public static Mortar factory() {
        return new Mortar();
    }
}
