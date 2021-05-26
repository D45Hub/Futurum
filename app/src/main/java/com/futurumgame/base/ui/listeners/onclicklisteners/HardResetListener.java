package com.futurumgame.base.ui.listeners.onclicklisteners;

import android.content.Context;
import android.view.View;

import com.futurumgame.base.gameinternals.GameRoutine;

public class HardResetListener  extends SoundListener {

    private final Context context;

    private HardResetListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        GameRoutine.requestHardReset();
    }

    public static HardResetListener newListener(Context context) {
        return new HardResetListener(context);
    }
}
