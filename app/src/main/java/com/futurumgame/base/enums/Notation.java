package com.futurumgame.base.enums;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.interfaces.INotationFormatter;

import java.lang.reflect.Method;

public enum Notation {
    Scientific(u -> {
        if (u.isNaN()) return "NaN";
        if (u.isPosInfinity()) return "Inf";
        if (u.isNegInfinity()) return "-Inf";
        if (u.isZero()) return "0";
        return String.format("%.2fe%.0f", u.getValue(), u.getScale());
    });

    private INotationFormatter formatter;

    private Notation(INotationFormatter formatter) {
        this.formatter = formatter;
    }

    public String applyNotationFormat(Units units) {
        return formatter.format(units);
    }
}
