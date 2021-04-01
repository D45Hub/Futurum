package com.futurumgame.base.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.advanced.Concrete;
import com.futurumgame.base.resources.alloys.Steel;
import com.futurumgame.base.resources.jewels.UnpolishedDiamond;
import com.futurumgame.base.resources.metals.Iron;

public class UnpolishedDiamondUnlockable extends ResourceUnlockable {

    private UnpolishedDiamondUnlockable() {
        super(UnpolishedDiamondUnlockable.class.getSimpleName(), UnpolishedDiamond.ID, new Units(1, 2),
                ResourceHelper.setToAmount(Steel.factory(), new Units(1, 12)),
                ResourceHelper.setToAmount(Iron.factory(), new Units(1,12)),
                ResourceHelper.setToAmount(Concrete.factory(), new Units(1, 20)));
    }

    public static UnpolishedDiamondUnlockable factory(){
        return new UnpolishedDiamondUnlockable();
    }
}
