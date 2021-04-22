package com.futurumgame.base.enums;

public enum Separator {
    DefaultSeparator(";"), CollectionSeparator("\\|"), DataStructureSeparator("#"), SequenceSeparator(", "), LineSeparator("\n");

    private static final String Backslash = "\\\\";
    private static final String Empty = "";

    private final String separator;

    private Separator(String separator){
        this.separator = separator;
    }

    public String getSeparator() {
        return separator;
    }

    public String getUnescapedSeparator() {
        return separator.replaceAll(Backslash,Empty);
    }
}
