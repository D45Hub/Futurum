package com.futurumgame.base.factories;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.resources.Jewel;

public abstract class PolishingFacility<T extends Jewel> extends Facility<T> {

    protected PolishingFacility(String name, T resource, Units internalCapacity) {
        super(name, resource, internalCapacity);
    }
}
