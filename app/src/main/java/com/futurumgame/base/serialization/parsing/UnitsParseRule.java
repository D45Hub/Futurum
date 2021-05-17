package com.futurumgame.base.serialization.parsing;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.normedimplementations.NormedNotationParser;

public class UnitsParseRule extends BaseParseRule<Units> {

    private static final char E = 'e';

    private boolean parseExponent;
    private double uValue;
    private double uExp;

    public UnitsParseRule() {
    }

    @Override
    public ParseResult<Units> next(char c) {
        if(Character.toLowerCase(c) == E) {
            clearAll();
            parseExponent = true;
            return ParseResult.failResult();
        }
        super.next(c);
        Units normedValue = NormedNotationParser.Singleton.parse(getReadChars());
        if(normedValue!= null) {
            clearAll();
            return ParseResult.create(normedValue);
        }
        double value;
        try {
            value = Double.parseDouble(getReadChars());
        }catch (NumberFormatException e) {
            return ParseResult.failResult();
        }
        if(parseExponent) {
            uExp = value;
        }else {
            uValue = value;
        }
        return ParseResult.create(new Units(uValue, uExp));
    }

    @Override
    public String getParsingValue(Units obj) {
        return obj.toString();
    }

    @Override
    protected void clear() {
        parseExponent = false;
    }
}
