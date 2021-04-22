package com.futurumgame.base.util;

import com.futurumgame.base.exceptions.UnitsFormatException;

public class ExceptionUtil {

    private ExceptionUtil() {
    }

    public static void throwUnitsFormatException(String s) throws UnitsFormatException {
        throw new UnitsFormatException("For input string: \"" + s + "\"");
    }
}
