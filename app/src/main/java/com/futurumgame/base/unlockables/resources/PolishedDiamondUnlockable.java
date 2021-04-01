package com.futurumgame.base.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.alloys.Steel;
import com.futurumgame.base.resources.jewels.PolishedDiamond;
import com.futurumgame.base.resources.ores.DiamondOre;

public class PolishedDiamondUnlockable extends ResourceUnlockable {

    private PolishedDiamondUnlockable() {
        super(PolishedDiamondUnlockable.class.getSimpleName(), PolishedDiamond.ID, new Units(1, 1),
                ResourceHelper.setToAmount(Steel.factory(), new Units(1, 15)),
                ResourceHelper.setToAmount(DiamondOre.factory(), new Units(1, 12)));
    }

    public static PolishedDiamondUnlockable factory(){
        return new PolishedDiamondUnlockable();
    }
}
