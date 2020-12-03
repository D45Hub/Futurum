package com.futurumgame.base.ui.listeners.onclicklisteners;

import android.app.Activity;
import android.view.View;

import com.futurumgame.base.gameinternals.GameRoutine;

public final class GoBackListener implements View.OnClickListener {

    private GoBackListener(){
    }

    @Override
    public void onClick(View v) {
        GameRoutine.fallBackToPrevious();
    }

    public static GoBackListener newListener(){
        return new GoBackListener();
    }
}
