package com.futurumgame.base.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.advanced.Wood;
import com.futurumgame.base.resources.basic.Dirt;
import com.futurumgame.base.resources.basic.Water;

public class WoodUnlockable extends ResourceUnlockable {

    private WoodUnlockable() {
        super(WoodUnlockable.class.getSimpleName(), Wood.ID, new Units(1, 3),
                ResourceHelper.setToAmount(Water.factory(), new Units(1, 5)),
                ResourceHelper.setToAmount(Dirt.factory(), new Units(1, 4)));
    }

    public static WoodUnlockable factory(){
        return new WoodUnlockable();
    }
}
