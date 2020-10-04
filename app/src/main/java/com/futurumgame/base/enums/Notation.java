package com.futurumgame.base.enums;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.interfaces.INotationFormatter;

import java.lang.reflect.Method;

public enum Notation {
    Scientific(u -> {
        if(u.isNaN()) return "NaN";
        if(u.isPosInfinity()) return "Inf";
        if(u.isNegInfinity()) return "-Inf";
        if(u.isZero()) return "0";
        String value = removeIllegalChars(String.format("%f",u.getValue()));
        return String.format("%se%f",value, u.getScale());
    });

    private static final char[] ForbiddenChars = {',', '.'};

    private INotationFormatter formatter;

    private Notation(INotationFormatter formatter) {
        this.formatter = formatter;
    }

    public String applyNotationFormat(Units units) {
        return formatter.format(units);
    }

    private static String removeIllegalChars(String s) {
        for (char c : ForbiddenChars) {
            s = s.replace(new String(new char[]{c}), "");
        }
        return s;
    }
}
