package com.futurumgame.base.ui.listeners.onclicklisteners;

import android.view.View;

import com.futurumgame.base.gameinternals.FactoryNode;
import com.futurumgame.base.gameinternals.GameRoutine;
import com.futurumgame.base.resources.Resource;

public class ManualCollectClickListener  extends SoundListener {

    private final FactoryNode<?extends Resource>node;

    private ManualCollectClickListener(FactoryNode<? extends Resource> node){
        this.node = node;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Resource factoryStocks = node.getCurrent().emptyStorage();
        GameRoutine.getWareHouse().addToResources(factoryStocks);
    }

    public static ManualCollectClickListener newListener(FactoryNode<?extends Resource> node){
        return new ManualCollectClickListener(node);
    }
}
