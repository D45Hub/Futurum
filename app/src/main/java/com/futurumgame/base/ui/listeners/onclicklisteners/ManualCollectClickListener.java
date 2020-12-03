package com.futurumgame.base.ui.listeners.onclicklisteners;

import android.view.View;

import com.futurumgame.base.gameinternals.FactoryNode;
import com.futurumgame.base.gameinternals.GameRoutine;
import com.futurumgame.base.resources.Resource;

public class ManualCollectClickListener implements View.OnClickListener {

    private final FactoryNode<?extends Resource>node;

    private ManualCollectClickListener(FactoryNode<? extends Resource> node){
        this.node = node;
    }

    @Override
    public void onClick(View v) {
        Resource factoryStocks = node.getCurrent().emptyStorage();
        GameRoutine.getCurrentWareHouse().addToResources(factoryStocks);
    }

    public static ManualCollectClickListener newListener(FactoryNode<?extends Resource> node){
        return new ManualCollectClickListener(node);
    }
}
