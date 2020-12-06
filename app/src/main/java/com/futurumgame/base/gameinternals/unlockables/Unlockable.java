package com.futurumgame.base.gameinternals.unlockables;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.collections.CollectionHelper;
import com.futurumgame.base.enums.UnlockableType;
import com.futurumgame.base.gameinternals.GameRoutine;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.basic.Dirt;
import com.futurumgame.base.resources.basic.Water;

import java.util.LinkedList;

public abstract class Unlockable {

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
}
