package com.futurumgame.base.ui.listeners.onclicklisteners;

import android.view.View;
import android.widget.TextView;

import com.futurumgame.base.enums.UpgradeResult;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.gameinternals.GameRoutine;
import com.futurumgame.base.resources.Resource;

public class UpgradeFactoryListener implements View.OnClickListener {

    private final Factory<? extends Resource> factory;
    private final TextView costs;

    private UpgradeFactoryListener(Factory<? extends Resource> factory, TextView costs){
        this.factory = factory;
        this.costs = costs;
    }

    @Override
    public void onClick(View v) {
        UpgradeResult result = factory.tryUpgrade(GameRoutine.getWareHouse());
        result.createPopup(v.getContext());
    }

    public static UpgradeFactoryListener newListener(Factory<? extends Resource> factory, TextView costs){
        return new UpgradeFactoryListener(factory, costs);
    }
}
