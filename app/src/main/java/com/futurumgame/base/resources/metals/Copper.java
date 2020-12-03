package com.futurumgame.base.resources.metals;

import com.futurumgame.base.resources.Metal;

public class Copper extends Metal {

    private Copper() {
        super(14, Copper.class.getSimpleName());
    }

    public static Copper factory() {
        return new Copper();
    }
}
