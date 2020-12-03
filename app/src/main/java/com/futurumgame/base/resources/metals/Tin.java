package com.futurumgame.base.resources.metals;

import com.futurumgame.base.resources.Metal;
import com.futurumgame.base.resources.ores.Coal;

public class Tin extends Metal {

    private Tin() {
        super(13, Tin.class.getSimpleName());
    }

    public static Tin factory() {
        return new Tin();
    }
}
