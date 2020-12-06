package com.futurumgame.base.gameinternals.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.gameinternals.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.alloys.Steel;
import com.futurumgame.base.resources.basic.Dirt;
import com.futurumgame.base.resources.basic.Water;
import com.futurumgame.base.resources.metals.Iron;
import com.futurumgame.base.resources.metals.Silver;

public class SilverUnlockable extends ResourceUnlockable {

    private SilverUnlockable() {
        super(SilverUnlockable.class.getSimpleName(), Silver.ID, new Units(1, 13),
                ResourceHelper.setToAmount(Steel.factory(), new Units(1, 8)),
                ResourceHelper.setToAmount(Iron.factory(), new Units(1, 35)));
    }

    public static SilverUnlockable factory(){
        return new SilverUnlockable();
    }
}
