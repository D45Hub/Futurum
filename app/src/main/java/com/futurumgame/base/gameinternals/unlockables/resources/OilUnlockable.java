package com.futurumgame.base.gameinternals.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.gameinternals.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.basic.Oil;
import com.futurumgame.base.resources.metals.Iron;

public class OilUnlockable extends ResourceUnlockable {

    private OilUnlockable() {
        super(OilUnlockable.class.getSimpleName(), Oil.ID, new Units(1, 3),
                ResourceHelper.setToAmount(Iron.factory(), new Units(1, 25)));
    }

    public static OilUnlockable factory(){
        return new OilUnlockable();
    }
}
