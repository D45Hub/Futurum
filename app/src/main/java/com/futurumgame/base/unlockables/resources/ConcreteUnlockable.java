package com.futurumgame.base.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.resources.ores.Coal;
import com.futurumgame.base.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.advanced.Concrete;
import com.futurumgame.base.resources.basic.Water;

public class ConcreteUnlockable extends ResourceUnlockable {

    private ConcreteUnlockable() {
        super(ConcreteUnlockable.class.getSimpleName(), Concrete.ID, new Units(1, 3),
                ResourceHelper.setToAmount(Coal.factory(), new Units(1, 3)));
    }

    public static ConcreteUnlockable factory(){
        return new ConcreteUnlockable();
    }
}
