package com.futurumgame.base.serialization.parsing;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.collections.PseudoMapEntry;
import com.futurumgame.base.enums.Separator;
import com.futurumgame.base.exceptions.UnitsFormatException;
import com.futurumgame.base.util.Logger;
import com.futurumgame.base.util.StringUtil;

import java.util.Map;

public class IntegerUnitsMapEntryParseRule extends MapEntryParseRule<Integer, Units> {

    public IntegerUnitsMapEntryParseRule() {
    }

    @Override
    public ParseResult<Map.Entry<Integer, Units>> next(String string) {
        super.next(string);
        if (getReadChars().isEmpty()) {
            return ParseResult.failResult();
        }
        String[] data = getReadChars().split(Separator.DataStructureSeparator.getSeparator());
        if (data.length < 2) {
            Logger.toFewData(getClass(), "capacity entry", data, 2);
            clearReadChars();
            return ParseResult.failResult();
        }
        if (data.length > 2) {
            Logger.toMuchData(getClass(), getReadChars());
        }
        int id=-1;
        Units cap;
        ParseResult<Map.Entry<Integer, Units>> result;
        try {
            id = Integer.parseInt(data[0]);
            cap = Units.parse(data[1]);
            result = ParseResult.create(PseudoMapEntry.create(id, cap));
        } catch (UnitsFormatException e) {
            Logger.cannotParse(getClass(), "capacity for resource id " + id, data[1], e);
            result = ParseResult.failResult();
        } catch (NumberFormatException e) {
            Logger.cannotParse(getClass(), "resource id", data[0], e);
            result = ParseResult.failResult();
        }
        clearReadChars();
        return result;
    }

    @Override
    public String getParsingValue(Map.Entry<Integer, Units> obj) {
        return StringUtil.combine(Separator.DataStructureSeparator, obj.getKey(), obj.getValue());
    }
}
