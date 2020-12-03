package com.futurumgame.base.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.futurumgame.base.R;
import com.futurumgame.base.collections.CollectionHelper;
import com.futurumgame.base.enums.Colors;
import com.futurumgame.base.enums.MetaData;
import com.futurumgame.base.enums.ResourceFormatter;
import com.futurumgame.base.enums.StringFormatter;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.gameinternals.FactoryNode;
import com.futurumgame.base.gameinternals.GameRoutine;
import com.futurumgame.base.gameinternals.WareHouse;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.ui.listeners.onclicklisteners.GoBackToMainActivityListener;
import com.futurumgame.base.ui.listeners.onclicklisteners.GoToViewListener;
import com.futurumgame.base.ui.listeners.onclicklisteners.UpgradeFactoryListener;
import com.futurumgame.base.util.ResourceUtil;
import com.futurumgame.base.util.UiUtil;

public class FactoryManagerViewActivity extends UpdatableViewActivity {

    private TextView costs;
    private Factory<? extends Resource> factory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.factory_manager_view);

        GameRoutine.setCurrent(this);
        factory = ((FactoryNode<? extends Resource>) MetaData.FactoryNode.getMeta()).getCurrent();
        costs = findViewById(R.id.UpgradeCosts);
        allowUpdates();

        //Button upgrade = findViewById(R.id.Upgrade);
        //upgrade.setOnClickListener(UpgradeFactoryListener.newListener(factory, costs));
        ImageButton upgradeButton = findViewById(R.id.upgradeButton);
        upgradeButton.setOnClickListener(UpgradeFactoryListener.newListener(factory, costs));

        //Button goBack =findViewById(R.id.GoBackToRecourceView);
        //goBack.setOnClickListener(new GoToViewListener(ResourceViewActivity.class, this));
        ImageButton goBack = findViewById(R.id.backButton);
        goBack.setOnClickListener(new GoToViewListener(ResourceViewActivity.class, this));
    }

    @Override
    public void updateUi(WareHouse wareHouse) {
        if (!updatesAllowed()) {
            return;
        }
        costs.setTextColor(factory.canUpgrade(wareHouse) ? Colors.Green.getColor() : Colors.Red.getColor());
        costs.setText(StringFormatter.NameValue.format(
                ResourceUtil.getText(costs.getContext(), R.string.Costs),
                CollectionHelper.toString(factory.getUpgradeCosts(), ResourceFormatter.Default::format)));
    }
}
