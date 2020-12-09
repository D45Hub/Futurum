package com.futurumgame.base.factories;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.resources.Ore;

public abstract class Mine<T extends Ore> extends Factory<T> {

    protected Mine(String name, T resource, Units internalCapacity) {
        super(name, resource, internalCapacity);
    }
}
