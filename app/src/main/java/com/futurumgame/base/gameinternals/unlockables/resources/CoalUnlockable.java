package com.futurumgame.base.gameinternals.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.gameinternals.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.advanced.Stone;
import com.futurumgame.base.resources.ores.Coal;

public class CoalUnlockable extends ResourceUnlockable {

    private CoalUnlockable() {
        super(CoalUnlockable.class.getSimpleName(), Coal.ID, new Units(1, 3),
                ResourceHelper.setToAmount(Stone.factory(), new Units(1, 3)));
    }

    public static CoalUnlockable factory(){
        return new CoalUnlockable();
    }
}
