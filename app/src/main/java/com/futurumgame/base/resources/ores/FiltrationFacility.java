package com.futurumgame.base.resources.ores;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.factories.Facility;
import com.futurumgame.base.resources.RawJewel;

public abstract class FiltrationFacility<T extends RawJewel> extends Facility<T> {

    protected FiltrationFacility(String name, T resource, Units internalCapacity) {
        super(name, resource, internalCapacity);
    }
}
