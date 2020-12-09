package com.futurumgame.base.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.alloys.Bronze;
import com.futurumgame.base.resources.ores.IronOre;

public class IronOreUnlockable extends ResourceUnlockable {

    private IronOreUnlockable() {
        super(IronOreUnlockable.class.getSimpleName(), IronOre.ID, new Units(1, 3),
                ResourceHelper.setToAmount(Bronze.factory(), new Units(1, 3)));
    }

    public static IronOreUnlockable factory(){
        return new IronOreUnlockable();
    }
}
