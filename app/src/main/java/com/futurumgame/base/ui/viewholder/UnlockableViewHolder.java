package com.futurumgame.base.ui.viewholder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.futurumgame.base.R;
import com.futurumgame.base.util.CollectionHelper;
import com.futurumgame.base.enums.Colors;
import com.futurumgame.base.enums.ResourceFormatter;
import com.futurumgame.base.ui.listeners.onclicklisteners.UnlockListener;
import com.futurumgame.base.unlockables.Unlockable;

public final class UnlockableViewHolder extends TextViewViewHolder<Unlockable> {

    private final TextView view;
    private final TextView costs;
    private final ImageButton buyButton;

    public UnlockableViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView.findViewById(R.id.ItemText);
        view.setTextColor(Colors.White.getColor());
        costs = itemView.findViewById(R.id.Prize);
        costs.setTextColor(Colors.White.getColor());
        buyButton = itemView.findViewById(R.id.UnlockButton);
    }

    public void updateText(Unlockable unlockable) {
        view.setText(unlockable.getName());
        costs.setText(CollectionHelper.toString(ResourceFormatter.Default::format, unlockable.getCosts()));
    }

    public void setOnClickListener(Unlockable unlockable){
        buyButton.setOnClickListener(UnlockListener.newListener(unlockable));
    }
}
