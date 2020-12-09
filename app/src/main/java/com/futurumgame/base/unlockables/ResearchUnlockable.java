package com.futurumgame.base.unlockables;

import com.futurumgame.base.resources.Resource;

public abstract class ResearchUnlockable extends Unlockable {

    protected ResearchUnlockable(String name, Resource... costs) {
        super(name, costs);
    }
}
