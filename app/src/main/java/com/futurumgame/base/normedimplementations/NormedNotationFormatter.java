package com.futurumgame.base.normedimplementations;

import com.futurumgame.base.additionalDatatypes.Units;

public class NormedNotationFormatter {

    public static final NormedNotationFormatter Singleton = new NormedNotationFormatter();

    private NormedNotationFormatter(){
    }

    public String format(Units unit){
        if (unit.isNaN()) return "NaN";
        if (unit.isPosInfinity()) return "Inf";
        if (unit.isNegInfinity()) return "-Inf";
        if (unit.isZero()) return "0";
        return null;
    }
}
