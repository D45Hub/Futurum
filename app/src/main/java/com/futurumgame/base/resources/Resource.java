package com.futurumgame.base.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.enums.ResourceFormatter;

import java.security.InvalidParameterException;

public abstract class Resource {

    protected final Units count = Units.Zero.copy();
    private final int id;
    private final String name;

    protected Resource(int id,String name) {
        this.id = id;
        this.name = name;
    }

    public Units getCount() {
        return count;
    }

    public int getID(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setCount(Units count) {
        this.count.setValue(count.getValue());
        this.count.setScale(count.getScale());
    }

    public abstract boolean isBaseResource();

    public final void add(Units units) {
        count.add(units);
    }

    public final void subtract(Units units){
        count.subtract(units);
    }

    public final void add(Resource more) {
        if (getClass() != more.getClass()){
            throw new InvalidParameterException("Resources have to be of the same type in order to be added together!");
        }
        count.add(more.count);
    }

    @Override
    public String toString() {
        return ResourceFormatter.Debug.format(this);
    }
}
