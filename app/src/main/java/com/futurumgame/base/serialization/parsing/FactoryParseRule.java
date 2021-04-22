package com.futurumgame.base.serialization.parsing;

import android.util.Log;

import com.futurumgame.base.enums.Separator;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.util.FactoryMapping;
import com.futurumgame.base.util.Logger;
import com.futurumgame.base.util.StringUtil;

public class FactoryParseRule<T extends Resource> extends BaseParseRule<Factory<T>> {

    private static final int DefaultLevel = 1;

    @Override
    public ParseResult<Factory<T>> next(String string) {
        super.next(string);
        String[] values = getReadChars().split(Separator.DataStructureSeparator.getSeparator());
        if (values.length < 2) {
            return ParseResult.create(null);
        }
        if (values.length != 2) {
            Logger.toMuchData(getClass(), getReadChars());
        }
        int level = DefaultLevel;
        try {
            level = Integer.parseInt(values[1]);
        } catch (NumberFormatException e) {
            Logger.cannotParse(getClass(), "factory level", values[0], DefaultLevel, e);
        }
        clearReadChars();
        return ParseResult.create(FactoryMapping.instanceFactory(values[0], level));
    }

    @Override
    public String getParsingValue(Factory<T> obj) {
        return StringUtil.combine(Separator.DataStructureSeparator, obj.getName(), obj.getLevel());
    }
}
