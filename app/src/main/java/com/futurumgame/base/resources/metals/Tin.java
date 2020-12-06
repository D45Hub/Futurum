package com.futurumgame.base.resources.metals;

import com.futurumgame.base.resources.Metal;
import com.futurumgame.base.resources.ores.Coal;

public class Tin extends Metal {

    public static final int ID = 14;

    private Tin() {
        super(ID, Tin.class.getSimpleName());
    }

    public static Tin factory() {
        return new Tin();
    }
}
