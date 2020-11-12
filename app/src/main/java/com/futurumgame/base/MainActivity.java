package com.futurumgame.base;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.futurumgame.base.gameinternals.WareHouse;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.ui.activities.UpdatableViewActivity;
import com.futurumgame.base.ui.adapter.ResourceAdapter;
import com.futurumgame.base.gameinternals.GameRoutine;

public class MainActivity extends UpdatableViewActivity {

    private GameRoutine gameRoutine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameRoutine = new GameRoutine(this);

        if(GameRoutine.getMainActivity()!= null){
            GameRoutine.setCurrent(GameRoutine.getMainActivity());
        }

        RecyclerView view = findViewById(R.id.Resources);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(new ResourceAdapter(gameRoutine.getWareHouse().getWareHouseStocks()));
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
        if(!updatesAllowed()){
            return;
        }
        RecyclerView view = findViewById(R.id.Resources);
        for (Resource warehouseStock:wareHouse.getWareHouseStocks().values()) {
            ((ResourceAdapter)view.getAdapter()).updateResourceViewHolder(warehouseStock);
        }
    }
}