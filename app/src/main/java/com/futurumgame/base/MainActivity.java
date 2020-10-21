package com.futurumgame.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.futurumgame.base.Ui.Adapter.ResourceAdapter;
import com.futurumgame.base.gameinternals.GameRoutine;

public class MainActivity extends AppCompatActivity {
    private GameRoutine gameRoutine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameRoutine = new GameRoutine(this);

        RecyclerView view = findViewById(R.id.Resources);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(new ResourceAdapter(gameRoutine.getWareHouse().getWareHouseStocks()));
        gameRoutine.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}