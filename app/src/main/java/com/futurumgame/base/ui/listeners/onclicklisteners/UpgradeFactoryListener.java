package com.futurumgame.base.ui.listeners.onclicklisteners;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.futurumgame.base.R;
import com.futurumgame.base.collections.CollectionHelper;
import com.futurumgame.base.enums.ResourceFormatter;
import com.futurumgame.base.enums.UpgradeResult;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.gameinternals.GameRoutine;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.util.UiUtil;

public class UpgradeFactoryListener implements View.OnClickListener {

    private final Factory<? extends Resource> factory;
    private final TextView costs;

    private UpgradeFactoryListener(Factory<? extends Resource> factory, TextView costs){
        this.factory = factory;
        this.costs = costs;
    }

    @Override
    public void onClick(View v) {
        UpgradeResult result = factory.tryUpgrade(GameRoutine.getCurrentWareHouse());
        result.createPopup(v.getContext());
    }

    public static UpgradeFactoryListener newListener(Factory<? extends Resource> factory, TextView costs){
        return new UpgradeFactoryListener(factory, costs);
    }
}
