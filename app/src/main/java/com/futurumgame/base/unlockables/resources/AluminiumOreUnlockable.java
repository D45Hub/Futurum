package com.futurumgame.base.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.metals.Iron;
import com.futurumgame.base.resources.ores.AluminiumOre;

public class AluminiumOreUnlockable extends ResourceUnlockable {

    private AluminiumOreUnlockable() {
        super(AluminiumOreUnlockable.class.getSimpleName(), AluminiumOre.ID, new Units(1, 3),
                ResourceHelper.setToAmount(Iron.factory(), new Units(1, 3)));
    }

    public static AluminiumOreUnlockable factory(){
        return new AluminiumOreUnlockable();
    }
}
