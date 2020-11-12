package com.futurumgame.base.ui.activities;

import android.app.Activity;

import com.futurumgame.base.gameinternals.WareHouse;

public abstract class UpdatableViewActivity extends Activity {

    private boolean canBeUpdated;

    protected void allowUpdates(){
        canBeUpdated = true;
    }

    protected boolean updatesAllowed(){
        return canBeUpdated;
    }

    public abstract void updateUi(WareHouse wareHouse);
}
