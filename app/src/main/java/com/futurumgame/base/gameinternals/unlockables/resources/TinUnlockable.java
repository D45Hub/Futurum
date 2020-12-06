package com.futurumgame.base.gameinternals.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.gameinternals.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.metals.Tin;
import com.futurumgame.base.resources.ores.TinOre;

public class TinUnlockable extends ResourceUnlockable {

    private TinUnlockable() {
        super(TinUnlockable.class.getSimpleName(), Tin.ID, new Units(1, 3),
                ResourceHelper.setToAmount(TinOre.factory(), new Units(1, 3)));
    }

    public static TinUnlockable factory(){
        return new TinUnlockable();
    }
}
