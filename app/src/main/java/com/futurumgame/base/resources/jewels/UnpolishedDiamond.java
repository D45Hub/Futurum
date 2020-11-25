package com.futurumgame.base.resources.jewels;

import com.futurumgame.base.resources.RawJewel;

public class UnpolishedDiamond extends RawJewel {

    private UnpolishedDiamond() {
        super(31, UnpolishedDiamond.class.getSimpleName());
    }

    public static UnpolishedDiamond factory(){
        return new UnpolishedDiamond();
    }
}
