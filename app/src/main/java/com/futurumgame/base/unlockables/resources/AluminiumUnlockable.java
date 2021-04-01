package com.futurumgame.base.unlockables.resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.unlockables.ResourceUnlockable;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.advanced.Coke;
import com.futurumgame.base.resources.advanced.Concrete;
import com.futurumgame.base.resources.alloys.Steel;
import com.futurumgame.base.resources.metals.Aluminium;
import com.futurumgame.base.resources.metals.Iron;

public class AluminiumUnlockable extends ResourceUnlockable {

    private AluminiumUnlockable() {
        super(AluminiumUnlockable.class.getSimpleName(), Aluminium.ID, new Units(1, 5),
                ResourceHelper.setToAmount(Steel.factory(), new Units(1, 10)),
                ResourceHelper.setToAmount(Coke.factory(), new Units(1, 30)),
                ResourceHelper.setToAmount(Concrete.factory(), new Units(1, 18)),
                ResourceHelper.setToAmount(Iron.factory(), new Units(1, 50)));
    }

    public static AluminiumUnlockable factory(){
        return new AluminiumUnlockable();
    }
}
