package com.futurumgame.base.resources.jewels;

import com.futurumgame.base.resources.Jewel;

public class PolishedDiamond extends Jewel {

    public static final int ID = 33;

    private PolishedDiamond() {
        super(ID, PolishedDiamond.class.getSimpleName());
    }

    public static PolishedDiamond factory(){
        return new PolishedDiamond();
    }
}
