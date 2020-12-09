package com.futurumgame.base.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.advanced.Stone;
import com.futurumgame.base.resources.ores.TinOre;

public class TinOreUnlockable extends ResourceUnlockable {

    private TinOreUnlockable() {
        super(TinOreUnlockable.class.getSimpleName(), TinOre.ID, new Units(1, 3),
                ResourceHelper.setToAmount(Stone.factory(), new Units(1, 3)));
    }

    public static TinOreUnlockable factory(){
        return new TinOreUnlockable();
    }
}
