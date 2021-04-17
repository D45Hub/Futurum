package com.futurumgame.base.serialization.datacreation;

import com.futurumgame.base.gameinternals.GameRoutine;
import com.futurumgame.base.gameinternals.WareHouse;
import com.futurumgame.base.interfaces.IDataCreator;

public class WareHouseDataCreator implements IDataCreator<WareHouse> {

    public static final WareHouseDataCreator Creator = new WareHouseDataCreator();

    private WareHouseDataCreator(){
    }

    @Override
    public byte[] createData() {
        return GameRoutine.getWareHouse().provideData();
    }
}
