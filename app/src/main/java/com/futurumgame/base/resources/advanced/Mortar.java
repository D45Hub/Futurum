package com.futurumgame.base.resources.advanced;

import com.futurumgame.base.resources.AdvancedResource;

public class Mortar extends AdvancedResource {

    private Mortar() {
        super(7, Mortar.class.getSimpleName());
    }

    public static Mortar factory() {
        return new Mortar();
    }
}
