package com.futurumgame.base.serialization.datacreation;

import com.futurumgame.base.gameinternals.GameRoutine;
import com.futurumgame.base.interfaces.IDataCreator;
import com.futurumgame.base.unlockables.UnlockableCollection;

public class UnlockDataCreator implements IDataCreator<UnlockableCollection> {

    public static final UnlockDataCreator Creator = new UnlockDataCreator();

    private UnlockDataCreator(){
    }

    @Override
    public byte[] createData() {
        return GameRoutine.getUnlockables().provideData();
    }
}
