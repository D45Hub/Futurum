package com.futurumgame.base.serialization.parsing;

import androidx.annotation.Nullable;

public class ParseResult<T> {

    private final T result;

    private ParseResult(@Nullable T result) {
        this.result = result;
    }

    public boolean parseSuccess(){
        return result != null;
    }

    public T getResult() {
        return result;
    }

    public static <T> ParseResult<T> create(T result) {
        return new ParseResult<>(result);
    }

    public static <T> ParseResult<T> failResult() {
        return new ParseResult<>(null);
    }
}
