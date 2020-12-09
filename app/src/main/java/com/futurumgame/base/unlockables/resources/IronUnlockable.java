package com.futurumgame.base.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.alloys.Bronze;
import com.futurumgame.base.resources.metals.Iron;

public class IronUnlockable extends ResourceUnlockable {

    private IronUnlockable() {
        super(IronUnlockable.class.getSimpleName(), Iron.ID, new Units(1, 3),
                ResourceHelper.setToAmount(Bronze.factory(), new Units(1, 3)));
    }

    public static IronUnlockable factory(){
        return new IronUnlockable();
    }
}
