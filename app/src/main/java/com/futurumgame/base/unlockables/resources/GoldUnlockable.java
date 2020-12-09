package com.futurumgame.base.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.alloys.Steel;
import com.futurumgame.base.resources.metals.Gold;
import com.futurumgame.base.resources.metals.Iron;

public class GoldUnlockable extends ResourceUnlockable {

    private GoldUnlockable() {
        super(GoldUnlockable.class.getSimpleName(), Gold.ID, new Units(1, 3),
                ResourceHelper.setToAmount(Steel.factory(), new Units(1, 8)),
                ResourceHelper.setToAmount(Iron.factory(), new Units(1, 35)));
    }

    public static GoldUnlockable factory(){
        return new GoldUnlockable();
    }
}
