package com.futurumgame.base.gameinternals.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.gameinternals.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.alloys.Steel;
import com.futurumgame.base.resources.basic.Water;
import com.futurumgame.base.resources.ores.DiamondOre;

public class DiamondOreUnlockable extends ResourceUnlockable {

    private DiamondOreUnlockable() {
        super(DirtUnlockable.class.getSimpleName(), DiamondOre.ID, new Units(1, 3),
                ResourceHelper.setToAmount(Steel.factory(), new Units(1, 3)));
    }

    public static DiamondOreUnlockable factory(){
        return new DiamondOreUnlockable();
    }
}
