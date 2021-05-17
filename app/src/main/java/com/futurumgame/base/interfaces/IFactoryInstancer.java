package com.futurumgame.base.interfaces;

import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.resources.Resource;

public interface IFactoryInstancer<T extends Resource,FT extends Factory<T>> {

    Factory<T> instance(int level);
}
