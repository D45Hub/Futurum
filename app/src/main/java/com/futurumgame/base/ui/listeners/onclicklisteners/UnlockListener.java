package com.futurumgame.base.ui.listeners.onclicklisteners;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.futurumgame.base.R;
import com.futurumgame.base.gameinternals.GameRoutine;
import com.futurumgame.base.gameinternals.WareHouse;
import com.futurumgame.base.ui.activities.UnlockViewActivity;
import com.futurumgame.base.unlockables.Unlockable;

public class UnlockListener  extends SoundListener {

    private final Unlockable unlockable;

    private UnlockListener(Unlockable unlockable) {
        this.unlockable = unlockable;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        WareHouse wareHouse = GameRoutine.getWareHouse();
        if (wareHouse.canOfferResources(unlockable.getCosts())) {
            wareHouse.offerResources(unlockable.getCosts());
            unlockable.unlock();
            RecyclerView unlockables = ((UnlockViewActivity)v.getContext()).findViewById(R.id.Unlockables);
            unlockables.getAdapter().notifyDataSetChanged();
        }
    }

    public static UnlockListener newListener(Unlockable unlockable) {
        return new UnlockListener(unlockable);
    }
}
