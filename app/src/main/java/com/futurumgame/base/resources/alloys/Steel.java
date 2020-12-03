package com.futurumgame.base.resources.alloys;

import com.futurumgame.base.resources.Alloy;
import com.futurumgame.base.resources.ores.DiamondOre;

public class Steel extends Alloy {

    private Steel() {
        super(27, Steel.class.getSimpleName());
    }

    public static Steel factory() {
        return new Steel();
    }
}
