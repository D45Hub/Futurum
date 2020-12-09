package com.futurumgame.base.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.advanced.FiredClay;
import com.futurumgame.base.resources.basic.Clay;

public class FiredClayUnlockable extends ResourceUnlockable {

    private FiredClayUnlockable() {
        super(FiredClayUnlockable.class.getSimpleName(), FiredClay.ID, new Units(1, 3),
                ResourceHelper.setToAmount(Clay.factory(), new Units(1, 3)));
    }

    public static FiredClayUnlockable factory(){
        return new FiredClayUnlockable();
    }
}
