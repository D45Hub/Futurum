package com.futurumgame.base.serialization.parsing;

import com.futurumgame.base.interfaces.IParseRule;

public abstract class BaseParseRule<T> implements IParseRule<T> {

    private String readChars;

    protected BaseParseRule() {
        clearAll();
    }

    public final String getReadChars(){
        return readChars;
    }

    @Override
    public ParseResult<T> next(char c) {
        readChars += c;
        return ParseResult.failResult();
    }

    @Override
    public ParseResult<T> next(String string) {
        readChars += string;
        return ParseResult.failResult();
    }

    public final void clearAll() {
        clear();
        readChars = "";
    }

    protected void clear() {
    }
}
