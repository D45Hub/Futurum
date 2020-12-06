package com.futurumgame.base.resources.metals;

import com.futurumgame.base.resources.Metal;
import com.futurumgame.base.resources.basic.Dirt;

public class Silver extends Metal {

    public static final int ID = 30;

    private Silver() {
        super(ID, Silver.class.getSimpleName());
    }

    public static Silver factory(){
        return new Silver();
    }
}
