package com.futurumgame.base.resources;

public abstract class BaseResource extends Resource {

    protected BaseResource(int id, String name) {
        super(id, name);
    }

    @Override
    public final boolean isBaseResource() {
        return true;
    }
}
