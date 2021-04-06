package com.futurumgame.base;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.futurumgame.base.gameinternals.WareHouse;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.ui.activities.UnlockViewActivity;
import com.futurumgame.base.ui.activities.UpdatableViewActivity;
import com.futurumgame.base.ui.adapter.ResourceAdapter;
import com.futurumgame.base.gameinternals.GameRoutine;
import com.futurumgame.base.ui.listeners.onclicklisteners.GoBackListener;
import com.futurumgame.base.ui.listeners.onclicklisteners.GoToViewListener;

public class MainActivity extends UpdatableViewActivity {

    private GameRoutine gameRoutine;
    private RecyclerView resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameRoutine = new GameRoutine(this);

        if (GameRoutine.getMainActivity() != null) {
            GameRoutine.setNewCurrent(GameRoutine.getMainActivity());
        }

        ImageButton unlockButton = findViewById(R.id.unlockButton);
        unlockButton.setOnClickListener(new GoToViewListener(UnlockViewActivity.class,this));

        resources = findViewById(R.id.Resources);
        resources.setLayoutManager(new LinearLayoutManager(this));
        resources.setAdapter(new ResourceAdapter(GameRoutine.getCurrentWareHouse().getWareHouseStocks()));
        allowUpdates();
        gameRoutine.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
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