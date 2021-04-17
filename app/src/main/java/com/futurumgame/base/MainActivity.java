package com.futurumgame.base;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.futurumgame.base.enums.DataFile;
import com.futurumgame.base.gameinternals.GameRoutine;
import com.futurumgame.base.gameinternals.WareHouse;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.serialization.AndroidFileHelper;
import com.futurumgame.base.ui.activities.UnlockViewActivity;
import com.futurumgame.base.ui.activities.UpdatableViewActivity;
import com.futurumgame.base.ui.adapter.ResourceAdapter;
import com.futurumgame.base.ui.listeners.onclicklisteners.GoToViewListener;

public class MainActivity extends UpdatableViewActivity {

    private GameRoutine gameRoutine;
    private RecyclerView resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidFileHelper.init(this);
        byte[] factorySystemData = DataFile.FactorySystem.read();
        byte[] unlockableDatas = DataFile.Unlockables.read();
        byte[] wareHouseData = DataFile.WareHouse.read();
        gameRoutine = new GameRoutine(this, factorySystemData, unlockableDatas, wareHouseData);

        if (GameRoutine.getMainActivity() != null) {
            GameRoutine.setNewCurrent(GameRoutine.getMainActivity());
        }

        ImageButton unlockButton = findViewById(R.id.unlockButton);
        unlockButton.setOnClickListener(new GoToViewListener(UnlockViewActivity.class, this));

        resources = findViewById(R.id.Resources);
        resources.setLayoutManager(new LinearLayoutManager(this));
        resources.setAdapter(new ResourceAdapter(GameRoutine.getWareHouse().getWareHouseStocks()));
        allowUpdates();
        gameRoutine.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GameRoutine.stop();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        for (DataFile file : DataFile.values()) {
            file.save();
        }
    }

    @Override
    public void updateUi(WareHouse wareHouse) {
        if (!updatesAllowed()) {
            return;
        }
        resources = findViewById(R.id.Resources);
        for (Resource warehouseStock : wareHouse.getWareHouseStocks().values()) {
            ((ResourceAdapter) resources.getAdapter()).updateResourceViewHolder(warehouseStock);
        }
    }
}