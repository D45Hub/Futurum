package com.futurumgame.base.ui.listeners.onclicklisteners;

import android.view.View;

import com.futurumgame.base.gameinternals.GameRoutine;
import com.futurumgame.base.gameinternals.WareHouse;
import com.futurumgame.base.unlockables.Unlockable;

public class UnlockListener implements View.OnClickListener {

    private final Unlockable unlockable;

    private UnlockListener(Unlockable unlockable) {
        this.unlockable = unlockable;
    }

    @Override
    public void onClick(View v) {
        WareHouse wareHouse = GameRoutine.getCurrentWareHouse();
        if (wareHouse.canOfferResources(unlockable.getCosts())) {
            wareHouse.offerResources(unlockable.getCosts());
            unlockable.unlock();
        }
    }

    public static UnlockListener newListener(Unlockable unlockable) {
        return new UnlockListener(unlockable);
    }
}
