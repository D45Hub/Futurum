package com.futurumgame.base.resources.ores;

import com.futurumgame.base.interfaces.IBurnable;
import com.futurumgame.base.resources.Ore;

public class Coal extends Ore implements IBurnable {

    public static final int ID = 13;

    private Coal() {
        super(ID, Coal.class.getSimpleName());
    }

    public static Coal factory() {
        return new Coal();
    }
}
