package com.futurumgame.base.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.metals.Copper;
import com.futurumgame.base.resources.ores.CopperOre;

public class CopperUnlockable extends ResourceUnlockable {

    private CopperUnlockable() {
        super(CopperUnlockable.class.getSimpleName(), Copper.ID, new Units(1, 3),
                ResourceHelper.setToAmount(CopperOre.factory(), new Units(1, 3)));
    }

    public static CopperUnlockable factory(){
        return new CopperUnlockable();
    }
}
