package com.futurumgame.base.resources.alloys;

import com.futurumgame.base.resources.Alloy;
import com.futurumgame.base.resources.advanced.Glass;

public class Bronze extends Alloy {

    private Bronze() {
        super(18, Bronze.class.getSimpleName());
    }

    public static Bronze factory() {
        return new Bronze();
    }
}
