package com.futurumgame.base.ui.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.futurumgame.base.R;
import com.futurumgame.base.enums.Colors;
import com.futurumgame.base.gameinternals.unlockables.Unlockable;

public final class UnlockableViewHolder extends TextViewViewHolder<Unlockable> {

    private TextView view;

    public UnlockableViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView.findViewById(R.id.ItemText);
        view.setTextColor(Colors.White.getColor());
    }

    public void updateText(Unlockable unlockable) {
        view.setText(unlockable.getName());
        view.setTextColor(Colors.White.getColor());
    }
}
