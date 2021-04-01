package com.futurumgame.base.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.advanced.Wood;
import com.futurumgame.base.resources.basic.Gravel;

public class GravelUnlockable extends ResourceUnlockable {

    private GravelUnlockable() {
        super(GravelUnlockable.class.getSimpleName(), Gravel.ID, new Units(1, 3),
                ResourceHelper.setToAmount(Wood.factory(), new Units(1, 3)));
    }

    public static GravelUnlockable factory(){
        return new GravelUnlockable();
    }
}
