package com.futurumgame.base.serialization.parsing;

import com.futurumgame.base.enums.Separator;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.util.FactoryMapping;
import com.futurumgame.base.util.Logger;
import com.futurumgame.base.util.StringUtil;

public class FactoryParseRule<T extends Resource> extends BaseParseRule<Factory<T>> {

    private static final int ExpectedValueCount = 2;
    private static final int DefaultLevel = 1;

    @Override
    public ParseResult<Factory<T>> next(String string) {
        super.next(string);
        String[] data = getReadChars().split(Separator.DataStructureSeparator.getSeparator());
        if (data.length < ExpectedValueCount) {
            Logger.toFewData(getClass(), "factory entry", data, ExpectedValueCount);
            return ParseResult.failResult();
        }
        if (data.length != ExpectedValueCount) {
            Logger.toMuchData(getClass(), getReadChars());
        }
        int level = DefaultLevel;
        try {
            level = Integer.parseInt(data[1]);
        } catch (NumberFormatException e) {
            Logger.cannotParse(getClass(), "factory level", data[0], DefaultLevel, e);
        }
        clearAll();
        return ParseResult.create(FactoryMapping.instanceFactory(data[0], level));
    }

    @Override
    public String getParsingValue(Factory<T> obj) {
        return StringUtil.combine(Separator.DataStructureSeparator, obj.getName(), obj.getLevel());
    }
}
