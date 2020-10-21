package com.futurumgame.base.enums;

import com.futurumgame.base.resources.Resource;

public enum ResourceFormatter {
    Default("%s: %s"), Debug("%s ID:%d Count: %s");

    private final String format;

    private ResourceFormatter(String format){
        this.format = format;
    }

    public String format(Resource resource){
        switch (this){
            case Debug:
                return String.format(format, resource.getName(),resource.getID(), resource.getCount());
            default:
                return String.format(format, resource.getName(), resource.getCount());
        }
    }
}
