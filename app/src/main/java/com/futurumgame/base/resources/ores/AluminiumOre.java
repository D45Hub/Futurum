package com.futurumgame.base.resources.ores;

import com.futurumgame.base.resources.Ore;

public class AluminiumOre extends Ore {

    public static final int ID = 26;

    private AluminiumOre() {
        super(ID, AluminiumOre.class.getSimpleName());
    }

    public static AluminiumOre factory(){
        return new AluminiumOre();
    }
}
