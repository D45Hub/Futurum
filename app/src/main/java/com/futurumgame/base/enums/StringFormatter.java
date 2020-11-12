package com.futurumgame.base.enums;

public enum StringFormatter {
    NameValue("%s: %s"),NameLinebreakValue("%s:%n%s"), NameValueUnit("%s: %s%s");

    private final String format;

    private StringFormatter(String format){
        this.format = format;
    }

    public String format(Object... objects) {
        return String.format(format,objects);
    }
}
