package com.futurumgame.base.gameinternals.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.gameinternals.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.advanced.Stone;
import com.futurumgame.base.resources.advanced.Wood;

public class StoneUnlockable extends ResourceUnlockable {

    private StoneUnlockable() {
        super(StoneUnlockable.class.getSimpleName(), Stone.ID, new Units(1, 3),
                ResourceHelper.setToAmount(Wood.factory(), new Units(1, 7)));
    }

    public static StoneUnlockable factory(){
        return new StoneUnlockable();
    }
}
