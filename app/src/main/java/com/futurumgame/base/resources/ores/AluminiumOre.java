package com.futurumgame.base.resources.ores;

import com.futurumgame.base.resources.Ore;

public class AluminiumOre extends Ore {

    private AluminiumOre() {
        super(25, AluminiumOre.class.getSimpleName());
    }

    public static AluminiumOre factory(){
        return new AluminiumOre();
    }
}
