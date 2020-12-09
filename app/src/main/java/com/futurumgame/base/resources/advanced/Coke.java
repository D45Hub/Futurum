package com.futurumgame.base.resources.advanced;

import com.futurumgame.base.interfaces.Burnable;
import com.futurumgame.base.resources.AdvancedResource;

public class Coke extends AdvancedResource implements Burnable {

    public static final int ID = 16;

    private Coke() {
        super(ID, Coke.class.getSimpleName());
    }

    public static Coke factory() {
        return new Coke();
    }
}
