package com.futurumgame.base.normedimplementations;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.interfaces.INormedNotation;
import com.futurumgame.base.interfaces.INotationFormatter;

public class NormedNotationFormatter implements INormedNotation, INotationFormatter {

    public static final NormedNotationFormatter Singleton = new NormedNotationFormatter();

    private NormedNotationFormatter(){
    }

    @Override
    public String format(Units unit){
        if (unit.isNaN()) return NaN;
        if (unit.isPosInfinity()) return PositiveInfinity;
        if (unit.isNegInfinity()) return NegativeInfinity;
        if (unit.isZero()) return Zero;
        return null;
    }
}
