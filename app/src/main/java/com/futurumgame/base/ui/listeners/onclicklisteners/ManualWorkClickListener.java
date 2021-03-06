package com.futurumgame.base.ui.listeners.onclicklisteners;

import android.util.Log;
import android.view.View;

import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.gameinternals.FactoryNode;
import com.futurumgame.base.resources.Resource;

public class ManualWorkClickListener  extends SoundListener {

    private final FactoryNode<? extends Resource> node;

    private ManualWorkClickListener(FactoryNode<? extends Resource> node){
        this.node = node;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        node.getCurrent().workManually();
    }

    public static ManualWorkClickListener newListener(FactoryNode<? extends Resource> node){
        return new ManualWorkClickListener(node);
    }
}
