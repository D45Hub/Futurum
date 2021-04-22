package com.futurumgame.base.normedimplementations;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.interfaces.INormedNotation;

import java.util.HashMap;

public class NormedNotationParser implements INormedNotation {

    public static final NormedNotationParser Singleton = new NormedNotationParser();

    private static final HashMap<String, Units> NormedValues = getNormedValues();

    private NormedNotationParser(){
    }

    public Units parse(String string) {
        return NormedValues.get(string);
    }

    private static HashMap<String, Units> getNormedValues() {
        HashMap<String, Units> normedValues = new HashMap<>();
        normedValues.put(NaN, Units.NaN);
        normedValues.put(PositiveInfinity, Units.PositiveInfinity);
        normedValues.put(NegativeInfinity, Units.NegativeInfinity);
        normedValues.put(Zero, Units.Zero);
        return normedValues;
    }
}
