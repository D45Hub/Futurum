package com.futurumgame.base.gameinternals.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.gameinternals.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.metals.Iron;
import com.futurumgame.base.resources.ores.SilverOre;

public class SilverOreUnlockable extends ResourceUnlockable {

    private SilverOreUnlockable() {
        super(SilverOreUnlockable.class.getSimpleName(), SilverOre.ID, new Units(1, 3),
                ResourceHelper.setToAmount(Iron.factory(), new Units(1, 3)));
    }

    public static SilverOreUnlockable factory(){
        return new SilverOreUnlockable();
    }
}
