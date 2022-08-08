package ru.otus.cachehw;


import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyCache<K, V> implements HwCache<K, V> {
    private static final Logger logger = LoggerFactory.getLogger(MyCache.class);
    private final Map<String, V> cacheMap = new WeakHashMap<>();
    private final Set<HwListener> listeners = new HashSet<>();

//Надо реализовать эти методы

    @Override
    public void put(K key, V value) {
        cacheMap.put(key.toString(), value);

    }

    @Override
    public void remove(K key) {
        cacheMap.remove(key.toString());
    }

    @Override
    public V get(K key) {
        return cacheMap.getOrDefault(key.toString(), null);
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        listeners.remove(listener);
    }
}
