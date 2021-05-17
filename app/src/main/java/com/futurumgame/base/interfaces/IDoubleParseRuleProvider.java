package com.futurumgame.base.interfaces;

public interface IDoubleParseRuleProvider<TFirst, TSecond> {

    IParseRule<TFirst> getFirstParseRule();

    IParseRule<TSecond> getSecondParseRule();
}
