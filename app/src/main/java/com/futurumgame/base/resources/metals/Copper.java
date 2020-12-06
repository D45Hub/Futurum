package com.futurumgame.base.resources.metals;

import com.futurumgame.base.resources.Metal;

public class Copper extends Metal {

    public static final int ID = 15;

    private Copper() {
        super(ID, Copper.class.getSimpleName());
    }

    public static Copper factory() {
        return new Copper();
    }
}
