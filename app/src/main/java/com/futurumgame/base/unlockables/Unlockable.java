package com.futurumgame.base.unlockables;

import com.futurumgame.base.collections.CollectionHelper;
import com.futurumgame.base.interfaces.IEquatable;
import com.futurumgame.base.resources.Resource;

import java.util.LinkedList;

public abstract class Unlockable implements IEquatable<Unlockable> {

    private final String name;
    private final LinkedList<Resource> costs = new LinkedList<>();

    protected Unlockable(String name, Resource... costs) {
        this.name = name;
        CollectionHelper.addAll(this.costs, costs);
    }

    public final String getName(){
        return name;
    }

    public final LinkedList<Resource> getCosts() {
        return costs;
    }

    public abstract void unlock();

    @Override
    public boolean isEqualTo(Unlockable other) {
        return name.equals(other.name) && CollectionHelper.equatableSequenceEquals(costs, other.costs);
    }
}
