package com.futurumgame.base.resources.jewels;

import com.futurumgame.base.resources.RawJewel;

public class UnpolishedDiamond extends RawJewel {

    public static final int ID = 32;

    private UnpolishedDiamond() {
        super(ID, UnpolishedDiamond.class.getSimpleName());
    }

    public static UnpolishedDiamond factory(){
        return new UnpolishedDiamond();
    }
}
