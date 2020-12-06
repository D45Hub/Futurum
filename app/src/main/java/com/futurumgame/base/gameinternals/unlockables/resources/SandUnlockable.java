package com.futurumgame.base.gameinternals.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.gameinternals.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.basic.Dirt;
import com.futurumgame.base.resources.basic.Sand;

public class SandUnlockable extends ResourceUnlockable {

    private SandUnlockable() {
        super(SandUnlockable.class.getSimpleName(), Sand.ID, new Units(1, 3),
                ResourceHelper.setToAmount(Dirt.factory(), new Units(1, 3)));
    }

    public static SandUnlockable factory(){
        return new SandUnlockable();
    }
}
