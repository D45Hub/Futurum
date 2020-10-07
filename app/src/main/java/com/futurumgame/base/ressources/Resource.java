package com.futurumgame.base.ressources;

import com.futurumgame.base.additionalDatatypes.Units;

public abstract class Resource {
    protected final Units count = Units.Zero;
    private final String name;

    protected Resource(String name) {
        this.name = name;
    }

    public Units getCount() {
        return count;
    }
}
