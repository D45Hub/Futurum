package com.futurumgame.base.serialization.parsing;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.enums.Separator;
import com.futurumgame.base.exceptions.UnitsFormatException;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.util.CollectionHelper;
import com.futurumgame.base.util.Logger;
import com.futurumgame.base.util.ResourceMapping;

public class ResourceParseRule extends BaseParseRule<Resource> {

    private static final int ExpectedValueCount = 2;

    public ResourceParseRule() {
    }

    @Override
    public ParseResult<Resource> next(String string) {
        super.next(string);
        if (getReadChars().isEmpty()) {
            return ParseResult.failResult();
        }
        String[] data = getReadChars().split(Separator.DataStructureSeparator.getSeparator());
        ParseResult<Resource> result = ParseResult.failResult();
        try {
            Integer id = Integer.parseInt(data[0]);
            result = ParseResult.create(ResourceMapping.AllResources.get(id));
            if (!result.parseSuccess()) {
                return result;
            }
            if (data.length > ExpectedValueCount) {
                Logger.toMuchData(getClass(), getReadChars());
            }
            result.getResult().setCount(Units.parse(data[1]));
        }  catch (UnitsFormatException | IndexOutOfBoundsException e ) {
            String message = "encountered error during resource parsing";
            if (e instanceof UnitsFormatException) {
                message += ", using Zero as count";
            }
            Logger.e(getClass(), message, e);
        } catch (NumberFormatException e) {
            Logger.cannotParse(getClass(), "resource", string);
            return ParseResult.failResult();
        } finally {
            clearAll();
            return result;
        }
    }

    @Override
    public String getParsingValue(Resource obj) {
        return CollectionHelper.toString(Separator.DataStructureSeparator, obj.getID(), obj.getCount());
    }
}
