package com.futurumgame.base.ui.activities;


import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.futurumgame.base.R;
import com.futurumgame.base.util.CollectionHelper;
import com.futurumgame.base.enums.Colors;
import com.futurumgame.base.enums.MetaData;
import com.futurumgame.base.enums.ResourceFormatter;
import com.futurumgame.base.enums.StringFormatter;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.gameinternals.FactoryNode;
import com.futurumgame.base.gameinternals.GameRoutine;
import com.futurumgame.base.gameinternals.WareHouse;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.ui.listeners.onclicklisteners.GoBackListener;
import com.futurumgame.base.ui.listeners.onclicklisteners.UpgradeFactoryListener;
import com.futurumgame.base.util.ResourceUtil;

public class FactoryManagerViewActivity extends UpdatableViewActivity {

    private TextView costs;
    private Factory<? extends Resource> factory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.factory_manager_view);

        GameRoutine.setNewCurrent(this);
        factory = ((FactoryNode<? extends Resource>) MetaData.FactoryNode.getMeta()).getCurrent();
        costs = findViewById(R.id.UpgradeCosts);
        allowUpdates();

        ImageButton upgradeButton = findViewById(R.id.upgradeButton);
        upgradeButton.setOnClickListener(UpgradeFactoryListener.newListener(factory, costs));

        ImageButton goBack = findViewById(R.id.backButton);
        goBack.setOnClickListener(GoBackListener.newListener());
    }

    @Override
    public void updateUi(WareHouse wareHouse) {
        if (!updatesAllowed()) {
            return;
        }
        costs.setTextColor(factory.canUpgrade(wareHouse) ? Colors.Green.getColor() : Colors.Red.getColor());
        costs.setText(StringFormatter.NameValue.format(
                ResourceUtil.getText(costs.getContext(), R.string.Costs),
                CollectionHelper.toString(ResourceFormatter.Default::format, factory.getUpgradeCosts())));
    }
}
