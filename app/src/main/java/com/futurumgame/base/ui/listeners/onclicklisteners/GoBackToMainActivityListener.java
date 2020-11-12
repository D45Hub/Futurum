package com.futurumgame.base.ui.listeners.onclicklisteners;

import android.app.Activity;
import android.view.View;

import com.futurumgame.base.MainActivity;
import com.futurumgame.base.gameinternals.GameRoutine;

public class GoBackToMainActivityListener extends GoToViewListener {

    public GoBackToMainActivityListener(Activity current) {
        super(MainActivity.class, current, true);
    }
}
