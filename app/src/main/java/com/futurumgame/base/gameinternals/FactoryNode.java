package com.futurumgame.base.gameinternals;

import android.content.Context;
import android.graphics.PointF;

import androidx.appcompat.widget.AppCompatButton;

import com.futurumgame.base.enums.Colors;
import com.futurumgame.base.enums.MetaData;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.interfaces.IParseRule;
import com.futurumgame.base.interfaces.IParseRuleProvider;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.serialization.parsing.FactoryNodeParseRule;
import com.futurumgame.base.serialization.parsing.FactoryParseRule;
import com.futurumgame.base.serialization.parsing.ParseResult;
import com.futurumgame.base.ui.activities.ResourceViewActivity;
import com.futurumgame.base.ui.listeners.onclicklisteners.GoToViewListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class FactoryNode<T extends Resource> extends AppCompatButton implements IParseRuleProvider<Factory<T>> {


    private final FactoryParseRule<T> parseRule = new FactoryParseRule<>();
    private final Stack<Factory<T>> fallbackFactories = new Stack<>();
    private final PointF position;
    private Factory<T> current;

    public FactoryNode(Context context, Factory<T> current, PointF position) {
        super(context);
        this.position = position;
        this.current = current;
        setBackgroundColor(Colors.Black.getColor());
        setText(current.getResource().getName());
        setTextColor(Colors.White.getColor());
        HashMap<String, Object> map = new HashMap<>();
        map.put(MetaData.FactoryNode.getMetaName(), this);
        setOnClickListener(new GoToViewListener(ResourceViewActivity.class, GameRoutine.getMainActivity(), map));
    }

    public ArrayList<Factory<T>> getFactories() {
        ArrayList<Factory<T>> factories = new ArrayList<>(fallbackFactories);
        factories.add(current);
        return factories;
    }

    public int getResourceID(){
        return current.getResource().getID();
    }

    public String getResourceName() {
        return current.getResource().getName();
    }

    public Factory<T> getCurrent() {
        return current;
    }

    @Override
    public IParseRule<Factory<T>> getParseRule() {
        return parseRule;
    }

    public void changePrimaryFactory(Factory<? extends Resource> factory) {
        fallbackFactories.push(current);
        //noinspection unchecked
        current = (Factory<T>) factory;
    }

    public boolean canFallBack(int fallBack) {
        return fallBack > fallbackFactories.size();
    }

    public Factory<? extends Resource> fallBack(int fallBack, WareHouse wareHouse) {
        Factory<? extends Resource> fallBackFactory = fallbackFactories.get(fallBack);
        if (fallBackFactory.gatherRequiredResources(wareHouse)) {
            return fallBackFactory;
        }
        return null;
    }
}
