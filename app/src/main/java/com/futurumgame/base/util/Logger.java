package com.futurumgame.base.util;

import android.util.Log;

public class Logger {

    private static final String ToMuchData = "to much data: ";
    private static final String CouldNotParseFormat = "could not parse '{0}', received data: '{1}'";
    private static final String CouldNotParseToFewData = CouldNotParseFormat + ", expected data count: '{2}', actual count: '{3}'";
    private static final String CouldNotParseDefaultValueFormat = CouldNotParseFormat + ", using default value: '{2}'";

    private Logger() {
    }

    public static void cannotParse(Class<?> clazz, String what, Object data) {
        Log.w(clazz.getSimpleName(), String.format(CouldNotParseFormat, what, data));
    }

    public static void cannotParse(Class<?> clazz, String what, Object data, Throwable throwable) {
        Log.w(clazz.getSimpleName(), String.format(CouldNotParseFormat, what, data), throwable);
    }

    public static void cannotParse(Class<?> clazz, String what, Object data, Object defaultValue){
        Log.w(clazz.getSimpleName(), String.format(CouldNotParseDefaultValueFormat, what, data, defaultValue));
    }

    public static void cannotParse(Class<?> clazz, String what, Object data, Object defaultValue, Throwable throwable){
        Log.w(clazz.getSimpleName(), String.format(CouldNotParseDefaultValueFormat, what, data, defaultValue), throwable);
    }

    public static void i(Class<?> clazz, String message){
        Log.i(clazz.getSimpleName(), message);
    }

    public static void e(Class<?> clazz, String message) {
        Log.e(clazz.getSimpleName(), message);
    }

    public static void e(Class<?> clazz, String message, Throwable throwable) {
        Log.e(clazz.getSimpleName(), message, throwable);
    }

    public static void toFewData(Class<?> clazz, String what, Object[] data, int expectedCount) {
        Log.w(clazz.getSimpleName(), String.format(CouldNotParseToFewData, what, CollectionHelper.toString(data), expectedCount, data.length));
    }

    public static void toMuchData(Class<?> clazz, Object data) {
        Log.w(clazz.getSimpleName(), ToMuchData + data);
    }
}
