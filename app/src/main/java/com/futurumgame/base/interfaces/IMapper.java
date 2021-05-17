package com.futurumgame.base.interfaces;

public interface IMapper<TKey, TValue> {

    TKey createKeyFor(TValue value);
}
