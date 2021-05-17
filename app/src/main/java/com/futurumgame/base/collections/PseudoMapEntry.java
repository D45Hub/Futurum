package com.futurumgame.base.collections;

import java.util.Map;

public class PseudoMapEntry<K, V> implements Map.Entry<K, V> {

    private V value;
    private final K key;

    private PseudoMapEntry(K key, V value){
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        return this.value = value;
    }

    public static<K, V> PseudoMapEntry<K, V> create(K key, V value){
        return new PseudoMapEntry<>(key, value);
    }
}
