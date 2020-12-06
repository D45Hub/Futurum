package com.futurumgame.base.gameinternals.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.gameinternals.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.advanced.Mortar;
import com.futurumgame.base.resources.basic.Dirt;
import com.futurumgame.base.resources.basic.Water;

public class MortarUnlockable extends ResourceUnlockable {

    private MortarUnlockable() {
        super(MortarUnlockable.class.getSimpleName(), Mortar.ID, new Units(1, 3),
                ResourceHelper.setToAmount(Water.factory(), new Units(1, 3)));
    }

    public static MortarUnlockable factory(){
        return new MortarUnlockable();
    }
}
