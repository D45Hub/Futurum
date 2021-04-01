package com.futurumgame.base.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.futurumgame.base.R;
import com.futurumgame.base.ui.viewholder.TextViewViewHolder;

public abstract class TextViewAdapter<T, S extends TextViewViewHolder<T>> extends RecyclerView.Adapter<S> {

    @Override
    public final void onBindViewHolder(@NonNull S holder, int position) {
        T holderObject = getHolderObjectByPosition(position);
        updateViewHolder(holder, holderObject);
        onBindViewHolder(holder, holderObject);
    }

    protected final View inflateListItemView(@NonNull ViewGroup parent, @LayoutRes int resource) {
        return LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
    }

    protected final void updateViewHolder(S holder, T holderObject){
        if(holder!= null) {
            holder.updateText(holderObject);
        }
    }

    protected abstract T getHolderObjectByPosition(int position);

    protected abstract void onBindViewHolder(S holder, T holderObject);
}
