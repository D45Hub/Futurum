package com.futurumgame.base.ui.activities;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.futurumgame.base.R;
import com.futurumgame.base.gameinternals.GameRoutine;
import com.futurumgame.base.gameinternals.WareHouse;
import com.futurumgame.base.ui.adapter.UnlockableAdapter;
import com.futurumgame.base.ui.listeners.onclicklisteners.GoBackListener;

public class UnlockViewActivity extends UpdatableViewActivity {

    RecyclerView unlockableContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unlock_view);
        GameRoutine.setNewCurrent(this);

        unlockableContainer = findViewById(R.id.Unlockables);
        unlockableContainer.setLayoutManager(new LinearLayoutManager(this));
        unlockableContainer.setAdapter(new UnlockableAdapter(GameRoutine.getUnlockables()));
        ImageButton goBack = findViewById(R.id.GoBack);
        goBack.setOnClickListener(GoBackListener.newListener());
        allowUpdates();
    }

    @Override
    public void updateUi(WareHouse wareHouse) {
    }
}
