package com.futurumgame.base.gameinternals.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.gameinternals.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.basic.Clay;
import com.futurumgame.base.resources.basic.Dirt;

public class ClayUnlockable extends ResourceUnlockable {

    private ClayUnlockable() {
        super(ClayUnlockable.class.getSimpleName(), Clay.ID, new Units(1, 3),
                ResourceHelper.setToAmount(Dirt.factory(), new Units(1, 3)));
    }

    public static ClayUnlockable factory(){
        return new ClayUnlockable();
    }
}
