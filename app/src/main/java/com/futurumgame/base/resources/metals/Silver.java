package com.futurumgame.base.resources.metals;

import com.futurumgame.base.resources.Metal;
import com.futurumgame.base.resources.basic.Dirt;

public class Silver extends Metal {

    private Silver() {
        super(29, Silver.class.getSimpleName());
    }

    public static Silver factory(){
        return new Silver();
    }
}
