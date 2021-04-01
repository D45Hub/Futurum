package com.futurumgame.base.resources.alloys;

import com.futurumgame.base.resources.Alloy;
import com.futurumgame.base.resources.ores.DiamondOre;

public class Steel extends Alloy {

    public static final int ID = 28;

    private Steel() {
        super(ID, Steel.class.getSimpleName());
    }

    public static Steel factory() {
        return new Steel();
    }
}
