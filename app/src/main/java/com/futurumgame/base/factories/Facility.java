package com.futurumgame.base.factories;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.resources.AdvancedResource;

public abstract class Facility<T extends AdvancedResource> extends Factory<T> {

    protected Facility(String name, T resource, Units internalCapacity) {
        super(name, resource, internalCapacity);
    }
}
