package com.futurumgame.base.factories;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.interfaces.IBurnable;
import com.futurumgame.base.resources.Metal;

public abstract class AlloySmeltery<T extends Metal, B extends IBurnable> extends Smeltery<T, B> {

    protected AlloySmeltery(String name, T resource, B burnable, Units internalCapacity) {
        super(name, resource, burnable, internalCapacity);
    }
}
