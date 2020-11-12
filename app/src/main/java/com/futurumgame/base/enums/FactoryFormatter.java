package com.futurumgame.base.enums;

import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.resources.Resource;

public enum FactoryFormatter {
    Default("%s Level: %d"), Debug("%s Level: %d Capacity: %s Storage: %s"), Multiline("Level %d %s%nCapacity: %s%nStorage: %s");

    private final String format;

    private FactoryFormatter(String format){
        this.format = format;
    }

    public String format(Factory<?extends Resource> factory){
        switch (this){
            case Debug:
                return String.format(format, factory.getName(), factory.getLevel(), factory.getCapacity(), factory.getStorage());
            case Multiline:
                return String.format(format, factory.getLevel(), factory.getName(), factory.getCapacity(), factory.getStorage());
            default:
             return String.format(format, factory.getName(), factory.getLevel());
        }
    }
}
