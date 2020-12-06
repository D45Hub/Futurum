package com.futurumgame.base.gameinternals.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.gameinternals.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.advanced.Glass;
import com.futurumgame.base.resources.ores.Coal;

public class GlassUnlockable extends ResourceUnlockable {

    private GlassUnlockable() {
        super(GlassUnlockable.class.getSimpleName(), Glass.ID, new Units(1, 3),
                ResourceHelper.setToAmount(Coal.factory(), new Units(1, 3)));
    }

    public static GlassUnlockable factory(){
        return new GlassUnlockable();
    }
}
