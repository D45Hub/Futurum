package com.futurumgame.base;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.futurumgame.base.enums.DataFile;
import com.futurumgame.base.gameinternals.GameRoutine;
import com.futurumgame.base.gameinternals.WareHouse;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.serialization.AndroidFileHelper;
import com.futurumgame.base.ui.activities.OptionsViewActivity;
import com.futurumgame.base.ui.activities.UnlockViewActivity;
import com.futurumgame.base.ui.activities.UpdatableViewActivity;
import com.futurumgame.base.ui.adapter.ResourceAdapter;
import com.futurumgame.base.ui.listeners.onclicklisteners.GoToViewListener;
import com.futurumgame.base.ui.listeners.onclicklisteners.HardResetListener;

public class MainActivity extends UpdatableViewActivity {

    private GameRoutine gameRoutine;
    private RecyclerView resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidFileHelper.init(this);
        gameRoutine = new GameRoutine(this);
        gameRoutine.initFromFiles();

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

        ImageButton settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new GoToViewListener(OptionsViewActivity.class, this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GameRoutine.stop();
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