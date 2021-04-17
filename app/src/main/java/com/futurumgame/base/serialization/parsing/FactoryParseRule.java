package com.futurumgame.base.serialization.parsing;

import android.util.Log;

import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.util.FactoryMapping;
import com.futurumgame.base.util.StringUtil;

public class FactoryParseRule<T extends Resource> extends BaseParseRule<Factory<T>> {

    @Override
    public ParseResult<Factory<T>> next(String string) {
        super.next(string);
        String[] values = getReadChars().split(StringUtil.DefaultDataStructureSeparator);
        if (values.length < 2) {
            return ParseResult.create(null);
        }
        if (values.length != 2) {
            Log.e(FactoryParseRule.class.getSimpleName(), "too many parse arguments: " + string);
        }
        int level = 1;
        try {
            level = Integer.parseInt(values[1]);
        } catch (NumberFormatException e) {
            Log.w(FactoryParseRule.class.getSimpleName(), "error during parse process initialising with level 0", e);
        }
        clearReadChars();
        return ParseResult.create(FactoryMapping.instanceFactory(values[0], level));
    }

    @Override
    public String getParsingValue(Factory<T> obj) {
        return StringUtil.combine(StringUtil.DefaultDataStructureSeparator, obj.getName(), obj.getLevel());
    }
}
