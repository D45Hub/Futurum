package com.futurumgame.base.resources;

public abstract class AdvancedResource extends Resource {

    protected AdvancedResource(int id, String name) {
        super(id, name);
    }

    @Override
    public final boolean isBaseResource() {
        return false;
    }
}
