package com.futurumgame.base.ressources;

import com.futurumgame.base.additionalDatatypes.Units;

public abstract class Ressource {
    protected final Units count = Units.Zero;
    private final String name;

    protected Ressource(String name) {
        this.name = name;
    }

    public Units getCount() {
        return count;
    }
}
