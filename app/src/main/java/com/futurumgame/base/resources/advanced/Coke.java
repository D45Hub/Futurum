package com.futurumgame.base.resources.advanced;

import com.futurumgame.base.resources.AdvancedResource;

public class Coke extends AdvancedResource {

    private Coke() {
        super(15, Coke.class.getSimpleName());
    }

    public static Coke factory() {
        return new Coke();
    }
}
