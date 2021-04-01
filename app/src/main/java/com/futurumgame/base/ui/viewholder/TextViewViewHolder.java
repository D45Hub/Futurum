package com.futurumgame.base.ui.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.futurumgame.base.R;
import com.futurumgame.base.enums.Colors;

public abstract class TextViewViewHolder<T> extends RecyclerView.ViewHolder {

    private TextView view;


    protected TextViewViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView.findViewById(R.id.ItemText);
        view.setTextColor(Colors.White.getColor());
    }

    public final int getViewID(){
        return view.getId();
    }

    public abstract void updateText(T viewObject);
}
