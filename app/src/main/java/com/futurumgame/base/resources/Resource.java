package com.futurumgame.base.resources;

import com.futurumgame.base.R;
import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.enums.ResourceFormatter;
import com.futurumgame.base.interfaces.IEquatable;
import com.futurumgame.base.interfaces.IParseRule;
import com.futurumgame.base.interfaces.IParseRuleProvider;
import com.futurumgame.base.resources.advanced.Wood;
import com.futurumgame.base.resources.basic.*;
import com.futurumgame.base.resources.metals.*;

import java.security.InvalidParameterException;
import java.util.Objects;

public abstract class Resource implements IEquatable<Resource> {

    protected final Units count = Units.Zero.copy();
    private final int id;
    private final String name;

    private final int resourceIconId;

    protected Resource(int id, String name) {
        this.id = id;
        this.name = name;

        this.resourceIconId = getInitialResourceIconId();
    }

    public int getID(){
        return id;
    }

    public int getResourceIconId() { return resourceIconId; }

    public String getName(){
        return name;
    }

    public Units getCount() {
        return count;
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
        return ResourceFormatter.Default.format(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resource resource = (Resource) o;
        return isEqualTo(resource);
    }

    @Override
    public boolean isEqualTo(Resource other) {
        return id == other.id && name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }


    private int getInitialResourceIconId() {
        int resourceIconId = R.drawable.water_icon;

        if (name.equals(Dirt.class.getSimpleName()) || name.equals(Sand.class.getSimpleName()) || name.equals(Clay.class.getSimpleName()) || name.equals(Gravel.class.getSimpleName())) {
            resourceIconId = R.drawable.soil_icon;
        }
        else if (name.equals(Wood.class.getSimpleName())) {
            resourceIconId = R.drawable.wood_icon;
        }
        else if (name.equals(Aluminium.class.getSimpleName()) || name.equals(Copper.class.getSimpleName()) || name.equals(Gold.class.getSimpleName()) || name.equals(Iron.class.getSimpleName()) || name.equals(Silver.class.getSimpleName()) || name.equals(Tin.class.getSimpleName())) {
            resourceIconId = R.drawable.ingot_base;
        }

        return resourceIconId;
    }
}
