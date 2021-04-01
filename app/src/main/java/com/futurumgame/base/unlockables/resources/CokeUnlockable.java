package com.futurumgame.base.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.advanced.Coke;
import com.futurumgame.base.resources.ores.Coal;

public class CokeUnlockable extends ResourceUnlockable {

    private CokeUnlockable() {
        super(CokeUnlockable.class.getSimpleName(), Coke.ID, new Units(1, 3),
                ResourceHelper.setToAmount(Coal.factory(), new Units(1, 3)));
    }

    public static CokeUnlockable factory(){
        return new CokeUnlockable();
    }
}
