package com.futurumgame.base.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.advanced.Stone;
import com.futurumgame.base.resources.ores.CopperOre;

public class CopperOreUnlockable extends ResourceUnlockable {

    private CopperOreUnlockable() {
        super(CopperOreUnlockable.class.getSimpleName(), CopperOre.ID, new Units(1, 3),
                ResourceHelper.setToAmount(Stone.factory(), new Units(1, 3)));
    }

    public static CopperOreUnlockable factory(){
        return new CopperOreUnlockable();
    }
}
