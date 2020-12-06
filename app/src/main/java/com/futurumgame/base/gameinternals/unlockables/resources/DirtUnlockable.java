package com.futurumgame.base.gameinternals.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.gameinternals.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.basic.Dirt;
import com.futurumgame.base.resources.basic.Water;

public class DirtUnlockable extends ResourceUnlockable {

    private DirtUnlockable() {
        super(DirtUnlockable.class.getSimpleName(), Dirt.ID, new Units(1, 3),
                ResourceHelper.setToAmount(Water.factory(), new Units(1, 3)));
    }

    public static DirtUnlockable factory(){
        return new DirtUnlockable();
    }
}
