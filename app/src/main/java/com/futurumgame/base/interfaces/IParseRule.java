package com.futurumgame.base.interfaces;

import com.futurumgame.base.serialization.parsing.ParseResult;

public interface IParseRule<T> {

    ParseResult<T> next(char c);

    ParseResult<T> next(String string);

    String getParsingValue(T obj);
}
