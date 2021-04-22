package com.futurumgame.base.util;

import com.futurumgame.base.collections.CollectionHelper;
import com.futurumgame.base.enums.Separator;

import java.util.ArrayList;

public class StringUtil {



    private StringUtil() {
    }

    public static String[] splitDefault(String string) {
        return string.split(Separator.DefaultSeparator.getSeparator());
    }

    public static String combine(String... strings) {
        return combine(Separator.DefaultSeparator, strings);
    }

    public static String combine(Separator separator, String... strings) {
        return CollectionHelper.toString(separator, strings);
    }

    public static String combine(Separator separator, Object... strings) {
        return CollectionHelper.toString(separator, strings);
    }

    public static char stringAsChar(String string){
        switch (string.length()){
            case 0:
                return Character.MIN_VALUE;
            case 1:
                return string.charAt(0);
            default:
                throw new IllegalArgumentException();
        }
    }

    public static String[][] doubleSplit(String string) {
        return doubleSplit(string, Separator.CollectionSeparator,  Separator.DefaultSeparator);
    }

    public static String[][] doubleSplit(String string, Separator firstSeparator, Separator secondSeparator) {
        String[] firstResults = string.split(firstSeparator.getSeparator());
        ArrayList<String[]> data = new ArrayList<>();
        for (String firstResult : firstResults) {
            data.add(firstResult.split(secondSeparator.getSeparator()));
        }
        return data.toArray(new String[data.size()][]);
    }

    public static String[][][] tripleSplit(String string) {
        return tripleSplit(string, Separator.CollectionSeparator, Separator.DefaultSeparator, Separator.DataStructureSeparator);
    }

    public static String[][][] tripleSplit(String string, Separator firstSeparator, Separator secondSeparator, Separator thirdSeparator) {
        String[] firstResults = string.split(firstSeparator.getSeparator());
        ArrayList<String[][]> data = new ArrayList<>();
        for (String firstResult : firstResults) {
            data.add(doubleSplit(firstResult, secondSeparator, thirdSeparator));
        }
        return data.toArray(new String[data.size()][][]);
    }
}
