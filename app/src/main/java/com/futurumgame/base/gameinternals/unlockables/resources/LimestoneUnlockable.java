package com.futurumgame.base.gameinternals.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.gameinternals.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.advanced.Limestone;
import com.futurumgame.base.resources.advanced.Stone;

public class LimestoneUnlockable extends ResourceUnlockable {

    private LimestoneUnlockable() {
        super(LimestoneUnlockable.class.getSimpleName(), Limestone.ID, new Units(1, 3),
                ResourceHelper.setToAmount(Stone.factory(), new Units(1, 5)));
    }

    public static LimestoneUnlockable factory(){
        return new LimestoneUnlockable();
    }
}
