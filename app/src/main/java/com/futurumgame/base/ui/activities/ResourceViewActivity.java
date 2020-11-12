package com.futurumgame.base.ui.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.futurumgame.base.R;
import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.enums.FactoryFormatter;
import com.futurumgame.base.enums.MetaData;
import com.futurumgame.base.enums.StringFormatter;
import com.futurumgame.base.gameinternals.FactoryNode;
import com.futurumgame.base.gameinternals.GameRoutine;
import com.futurumgame.base.gameinternals.WareHouse;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.ui.listeners.onclicklisteners.GoBackToMainActivityListener;
import com.futurumgame.base.ui.listeners.onclicklisteners.GoToViewListener;
import com.futurumgame.base.ui.listeners.onclicklisteners.ManualWorkClickListener;
import com.futurumgame.base.util.ResourceUtil;

public class ResourceViewActivity extends UpdatableViewActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resource_view);

        GameRoutine.setCurrent(this);
        allowUpdates();

        TextView resourceName = findViewById(R.id.ResourceName);
        resourceName.setText(((FactoryNode) MetaData.FactoryNode.getMeta()).getResourceName());
        Button manageFactory = findViewById(R.id.ManageFactory);
        manageFactory.setOnClickListener(new GoToViewListener(FactoryManagerViewActivity.class, this));
        Button click = findViewById(R.id.Click);
        click.setOnClickListener(ManualWorkClickListener.newListener(MetaData.FactoryNode.getMeta()));
        Button goBack = findViewById(R.id.GoBackToMainView);
        goBack.setOnClickListener(new GoBackToMainActivityListener(this));
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
        TextView warehouseStocks = findViewById(R.id.WarehouseStock);
        FactoryNode<? extends Resource> node = MetaData.FactoryNode.getMeta();
        warehouseStocks.setText(wareHouse.getWareHouseStock(node.getResourceID()).toString());
        TextView productionRate = findViewById(R.id.Production);
        Units production = node.getCurrent().work().getCount();
        final long ticksPerSecond = 1000 / GameRoutine.getTickRate();
        production.multiply(new Units(ticksPerSecond, 0));
        productionRate.setText(StringFormatter.NameValueUnit.format(
                ResourceUtil.getText(this, R.string.ProductionRate), production,
                ResourceUtil.getText(this, R.string.UnitsPerSecond)));
        TextView factoryOverview = findViewById(R.id.FactoryOveriew);
        factoryOverview.setText(StringFormatter.NameLinebreakValue.format(
                ResourceUtil.getText(this, R.string.Factory), FactoryFormatter.Multiline.format(node.getCurrent())));
    }
}
