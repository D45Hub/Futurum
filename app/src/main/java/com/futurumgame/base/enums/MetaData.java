package com.futurumgame.base.enums;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public enum MetaData {
    FactoryNode("FactoryNode");

    private static final HashMap<String, Object> meta = new HashMap<>();
    private final String metaName;

    private MetaData(String metaName){
        this.metaName = metaName;
    }

    public static void setAllMeta(Map<String, Object>metas) {
        meta.putAll(metas);
    }

    public String getMetaName(){
        return metaName;
    }

    public void setMeta(Object metaData){
        meta.put(metaName, metaData);
    }

    public <T> T getMeta() {
        return (T)meta.get(metaName);
    }
}
