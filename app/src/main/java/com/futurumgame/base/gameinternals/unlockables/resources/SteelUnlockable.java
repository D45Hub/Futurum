package com.futurumgame.base.gameinternals.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.gameinternals.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.advanced.Coke;
import com.futurumgame.base.resources.alloys.Steel;
import com.futurumgame.base.resources.metals.Iron;

public class SteelUnlockable extends ResourceUnlockable {

    private SteelUnlockable() {
        super(SteelUnlockable.class.getSimpleName(), Steel.ID, new Units(1, 3),
                ResourceHelper.setToAmount(Iron.factory(), new Units(1, 3)),
                ResourceHelper.setToAmount(Coke.factory(), new Units(1,5)));
    }

    public static SteelUnlockable factory(){
        return new SteelUnlockable();
    }
}
