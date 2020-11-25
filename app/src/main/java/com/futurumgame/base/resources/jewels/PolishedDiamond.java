package com.futurumgame.base.resources.jewels;

import com.futurumgame.base.resources.Jewel;

public class PolishedDiamond extends Jewel {

    private PolishedDiamond() {
        super(32, PolishedDiamond.class.getSimpleName());
    }

    public static PolishedDiamond factory(){
        return new PolishedDiamond();
    }
}
