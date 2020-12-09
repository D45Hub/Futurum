package com.futurumgame.base.resources.alloys;

import com.futurumgame.base.resources.Alloy;
import com.futurumgame.base.resources.advanced.Glass;

public class Bronze extends Alloy {

    public static final int ID = 19;

    private Bronze() {
        super(ID, Bronze.class.getSimpleName());
    }

    public static Bronze factory() {
        return new Bronze();
    }
}
