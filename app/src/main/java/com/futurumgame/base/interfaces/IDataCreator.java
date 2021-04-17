package com.futurumgame.base.interfaces;

public interface IDataCreator<T extends IDataProvider> {

    byte[] createData();
}
