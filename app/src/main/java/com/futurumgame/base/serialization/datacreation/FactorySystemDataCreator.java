package com.futurumgame.base.serialization.datacreation;

import com.futurumgame.base.MainActivity;
import com.futurumgame.base.gameinternals.FactorySystem;
import com.futurumgame.base.gameinternals.GameRoutine;
import com.futurumgame.base.interfaces.IDataCreator;

public class FactorySystemDataCreator implements IDataCreator<FactorySystem> {

    public static final FactorySystemDataCreator Creator = new FactorySystemDataCreator();

    private FactorySystemDataCreator() {
    }

    @Override
    public byte[] createData() {
        return GameRoutine.getFactorySystem().provideData();
    }
}
