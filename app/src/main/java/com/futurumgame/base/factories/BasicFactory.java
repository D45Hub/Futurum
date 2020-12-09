package com.futurumgame.base.factories;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.resources.BaseResource;
import com.futurumgame.base.resources.Resource;

import java.util.LinkedList;
import java.util.Queue;

public abstract class BasicFactory<T extends BaseResource> extends Factory<T> {

    private static final LinkedList<Integer> EmptyIDList = new LinkedList<>();
    private static final LinkedList<Resource> EmptyResourceList = new LinkedList<>();

    protected BasicFactory(String name, T resource, Units internalCapacity) {
        super(name, resource, internalCapacity);
    }

    @Override
    public final Queue<Integer> getLongestResourceTreePath() {
        return EmptyIDList;
    }

    @Override
    protected final LinkedList<Resource> requiredResources() {
        return EmptyResourceList;
    }
}
