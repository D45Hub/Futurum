package com.futurumgame.base.ui.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.futurumgame.base.R;
import com.futurumgame.base.gameinternals.unlockables.Unlockable;
import com.futurumgame.base.gameinternals.unlockables.UnlockableCollection;
import com.futurumgame.base.ui.viewholder.UnlockableViewHolder;

public final class UnlockableAdapter extends TextViewAdapter<Unlockable, UnlockableViewHolder> {

    private final UnlockableCollection unlockables;

    public UnlockableAdapter(UnlockableCollection unlockables){
        this.unlockables = unlockables;
    }

    @NonNull
    @Override
    public UnlockableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UnlockableViewHolder(inflateListItemView(parent, R.layout.list_price_item_view));
    }

    @Override
    public int getItemCount() {
        return unlockables.size();
    }

    @Override
    protected Unlockable getHolderObjectByPosition(int position) {
        return unlockables.get(position);
    }

    @Override
    protected void onBindViewHolder(UnlockableViewHolder holder, int position, Unlockable holderObject) {
    }
}
