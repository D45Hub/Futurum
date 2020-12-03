package com.futurumgame.base.ui.activities;

import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.futurumgame.base.R;
import com.futurumgame.base.enums.FactoryFormatter;
import com.futurumgame.base.enums.MetaData;
import com.futurumgame.base.enums.StringFormatter;
import com.futurumgame.base.gameinternals.FactoryNode;
import com.futurumgame.base.gameinternals.GameRoutine;
import com.futurumgame.base.gameinternals.WareHouse;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.ui.listeners.onclicklisteners.GoBackListener;
import com.futurumgame.base.ui.listeners.onclicklisteners.GoToViewListener;
import com.futurumgame.base.ui.listeners.onclicklisteners.ManualCollectClickListener;
import com.futurumgame.base.ui.listeners.onclicklisteners.ManualWorkClickListener;
import com.futurumgame.base.util.ResourceUtil;


public class ResourceViewActivity extends UpdatableViewActivity {

    private TextView warehouseStocks;
    private TextView productionRate;
    private TextView factoryOverview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resource_view);

        GameRoutine.setNewCurrent(this);
        allowUpdates();

        ImageButton manageFactoryButton = findViewById(R.id.factoryButton);
        manageFactoryButton.setOnClickListener(new GoToViewListener(FactoryManagerViewActivity.class, this));

        ImageButton clickButton = findViewById(R.id.clickButton);
        clickButton.setOnClickListener(ManualWorkClickListener.newListener(MetaData.FactoryNode.getMeta()));

        ImageButton goBackButton = findViewById(R.id.backButton2);
        goBackButton.setOnClickListener(GoBackListener.newListener());

        Button collectButton = findViewById(R.id.Collect);
        collectButton.setOnClickListener(ManualCollectClickListener.newListener(MetaData.FactoryNode.getMeta()));

        warehouseStocks = findViewById(R.id.WarehouseStock);
        productionRate = findViewById(R.id.Production);
        factoryOverview = findViewById(R.id.FactoryOveriew);

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void updateUi(WareHouse wareHouse) {
        if (!updatesAllowed()) {
            return;
        }
        FactoryNode<? extends Resource> node = MetaData.FactoryNode.getMeta();
        warehouseStocks.setText(wareHouse.getWareHouseStock(node.getResourceID()).toString());
        productionRate.setText(StringFormatter.NameValueUnit.format(
                ResourceUtil.getText(this, R.string.ProductionRate), GameRoutine.getMeasuredProduction(node.getResourceID()),
                ResourceUtil.getText(this, R.string.UnitsPerSecond)));
        factoryOverview.setText(StringFormatter.NameLinebreakValue.format(
                ResourceUtil.getText(this, R.string.Factory), FactoryFormatter.Multiline.format(node.getCurrent())));
    }
}
