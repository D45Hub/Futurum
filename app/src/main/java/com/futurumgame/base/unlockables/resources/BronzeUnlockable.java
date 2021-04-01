package com.futurumgame.base.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.alloys.Bronze;
import com.futurumgame.base.resources.metals.Copper;
import com.futurumgame.base.resources.metals.Tin;

public class BronzeUnlockable extends ResourceUnlockable {

    private BronzeUnlockable() {
        super(BronzeUnlockable.class.getSimpleName(), Bronze.ID, new Units(1, 3),
                ResourceHelper.setToAmount(Copper.factory(), new Units(1, 3)),
                ResourceHelper.setToAmount(Tin.factory(), new Units(1, 3)));
    }

    public static BronzeUnlockable factory(){
        return new BronzeUnlockable();
    }
}
