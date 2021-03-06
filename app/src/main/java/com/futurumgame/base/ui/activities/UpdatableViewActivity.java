package com.futurumgame.base.ui.activities;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.futurumgame.base.enums.DataFile;
import com.futurumgame.base.gameinternals.WareHouse;

import java.util.Arrays;

public abstract class UpdatableViewActivity extends Activity {

    private boolean canBeUpdated;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Arrays.stream(DataFile.values()).forEach(DataFile::save);
    }

    protected void allowUpdates(){
        canBeUpdated = true;
    }

    protected boolean updatesAllowed(){
        return canBeUpdated;
    }

    public abstract void updateUi(WareHouse wareHouse);
}
