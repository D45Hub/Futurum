package com.futurumgame.base.serialization.parsing;

import android.content.Context;
import android.graphics.PointF;

import com.futurumgame.base.util.CollectionHelper;
import com.futurumgame.base.enums.Separator;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.gameinternals.FactoryNode;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class FactoryNodeParseRule<T extends Resource> extends BaseParseRule<FactoryNode<T>> {

    private final FactoryParseRule<T> ParseRule = new FactoryParseRule<>();

    private final Context context;

    public FactoryNodeParseRule(Context context) {
        this.context = context;
    }

    @Override
    public ParseResult<FactoryNode<T>> next(String string) {
        super.next(string);
        String[] factories = getReadChars().split(Separator.DefaultSeparator.getSeparator());
        if(factories.length == 0) {
            return ParseResult.failResult();
        }
        LinkedList<ParseResult<Factory<T>>> successes = CollectionHelper.where(CollectionHelper.select(factories, ParseRule::next), r->r.parseSuccess());
        if(successes.isEmpty()) {
            clearAll();
            return ParseResult.failResult();
        }
        FactoryNode<T> node = new FactoryNode(context, successes.pop().getResult(), new PointF());
        for (ParseResult<Factory<T>> result: successes) {
            node.changePrimaryFactory(result.getResult());
        }
        clearAll();
        return ParseResult.create(node);
    }

    @Override
    public String getParsingValue(FactoryNode<T> obj) {
        ArrayList<String> data = new ArrayList<>();
        ArrayList<Factory<T>> factories = obj.getFactories();
        Collections.reverse(factories);
        for (Factory<T> factory : factories) {
            data.add(obj.getParseRule().getParsingValue(factory));
        }
        return StringUtil.combine(Separator.DefaultSeparator, data.toArray());
    }
}
