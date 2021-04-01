package com.futurumgame.base.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.advanced.Cement;
import com.futurumgame.base.resources.advanced.Stone;

public class CementUnlockable extends ResourceUnlockable {

    private CementUnlockable() {
        super(CementUnlockable.class.getSimpleName(), Cement.ID, new Units(1, 3),
                ResourceHelper.setToAmount(Stone.factory(), new Units(1, 3)));
    }

    public static CementUnlockable factory(){
        return new CementUnlockable();
    }
}
