package com.futurumgame.base.factories;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.interfaces.IBurnable;
import com.futurumgame.base.resources.Metal;
import com.futurumgame.base.resources.Resource;

import java.util.LinkedList;

public abstract class Smeltery<T extends Metal, B extends IBurnable> extends Factory<T> {

    private final Resource burnable;

    protected Smeltery(String name, T resource, B burnable, Units internalCapacity) {
        super(name, resource, internalCapacity);
        this.burnable = (Resource) burnable;
    }

    @Override
    protected final LinkedList<Resource> requiredResources() {
        LinkedList<Resource> requiredResources = new LinkedList<>();
        requiredResources.add(burnable);
        requiredResources.addAll(requiredSmeltingComponents());
        return requiredResources;
    }

    protected abstract LinkedList<Resource> requiredSmeltingComponents();
}
