package com.futurumgame.base.gameinternals.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.gameinternals.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.basic.Water;
import com.futurumgame.base.resources.ores.GoldOre;

public class GoldOreUnlockable extends ResourceUnlockable {

    private GoldOreUnlockable() {
        super(GoldOreUnlockable.class.getSimpleName(), GoldOre.ID, new Units(1, 3),
                ResourceHelper.setToAmount(Water.factory(), new Units(1, 3)));
    }

    public static GoldOreUnlockable factory(){
        return new GoldOreUnlockable();
    }
}
