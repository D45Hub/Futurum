package com.futurumgame.base.ui.activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.futurumgame.base.R;
import com.futurumgame.base.gameinternals.GameRoutine;
import com.futurumgame.base.ui.listeners.onclicklisteners.GoBackListener;
import com.futurumgame.base.unlockables.UnlockableCollection;
import com.futurumgame.base.gameinternals.WareHouse;
import com.futurumgame.base.ui.adapter.UnlockableAdapter;

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
        Button goBack = findViewById(R.id.GoBack);
        goBack.setOnClickListener(GoBackListener.newListener());
        allowUpdates();
    }

    @Override
    public void updateUi(WareHouse wareHouse) {
        UnlockableCollection unlockables = GameRoutine.getUnlockables();
        for (int i = 0; i < unlockables.size(); i++) {
            //boolean canOffer = wareHouse.canOfferResources(unlockables.get(i).getCosts());
            //unlockableContainer.getChildAt(i).findViewById(R.id.BuyButton).setEnabled(canOffer);
        }
    }
}
