package com.futurumgame.base.util;

import com.futurumgame.base.collections.CollectionHelper;

import java.util.ArrayList;

public class StringUtil {

    public static final String DefaultDataStructureSeparator = "#";
    public static final String DefaultDataCollectionSeparator = "\\|";
    public static final String DefaultSplitSeparator = ";";

    private StringUtil() {
    }

    public static String[] splitDefault(String string) {
        return string.split(DefaultSplitSeparator);
    }

    public static String combine(String... strings) {
        return combine(DefaultSplitSeparator, strings);
    }

    public static String combine(String separator, String... strings) {
        return CollectionHelper.toString(separator, strings);
    }

    public static String combine(String separator, Object... strings) {
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
        return doubleSplit(string, DefaultSplitSeparator, DefaultDataCollectionSeparator);
    }

    public static String[][] doubleSplit(String string, String firstSeparator, String secondSeparator) {
        String[] firstResults = string.split(firstSeparator);
        ArrayList<String[]> data = new ArrayList<>();
        for (String firstResult : firstResults) {
            data.add(firstResult.split(secondSeparator));
        }
        return data.toArray(new String[data.size()][]);
    }

    public static String[][][] tripleSplit(String string) {
        return tripleSplit(string, DefaultDataCollectionSeparator, DefaultSplitSeparator, DefaultDataStructureSeparator);
    }

    public static String[][][] tripleSplit(String string, String firstSeparator, String secondSeparator, String thirdSeparator) {
        String[] firstResults = string.split(firstSeparator);
        ArrayList<String[][]> data = new ArrayList<>();
        for (String firstResult : firstResults) {
            data.add(doubleSplit(string, secondSeparator, thirdSeparator));
        }
        return data.toArray(new String[data.size()][][]);
    }
}
