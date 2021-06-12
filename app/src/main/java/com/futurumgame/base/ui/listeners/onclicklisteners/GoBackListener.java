package com.futurumgame.base.ui.listeners.onclicklisteners;

import android.view.View;

import com.futurumgame.base.gameinternals.GameRoutine;

public final class GoBackListener extends SoundListener {

    private GoBackListener() {
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        GameRoutine.fallBackToPrevious();
    }

    public static GoBackListener newListener() {
        return new GoBackListener();
    }
}
