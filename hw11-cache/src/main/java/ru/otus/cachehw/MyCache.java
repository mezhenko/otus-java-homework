package ru.otus.cachehw;


import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyCache<K, V> implements HwCache<K, V> {
    private static final Logger logger = LoggerFactory.getLogger(MyCache.class);
    private static final String ACTION_CACHE_PUT = "cachePut";
    private static final String ACTION_CACHE_GET = "cacheGet";
    private static final String ACTION_CACHE_REMOVED = "cacheRemove";
    private final Map<String, V> cacheMap = new WeakHashMap<>();
    private final Set<HwListener<K, V>> listeners = new HashSet<>();

//Надо реализовать эти методы

    @Override
    public void put(K key, V value) {
        for (HwListener<K, V> listener : this.listeners) {
            listener.notify(key, value, ACTION_CACHE_PUT);
        }
        cacheMap.put(key.toString(), value);
    }

    @Override
    public void remove(K key) {
        for (HwListener<K, V> listener : this.listeners) {
            listener.notify(key, null, ACTION_CACHE_REMOVED);
        }
        cacheMap.remove(key.toString());
    }

    @Override
    public V get(K key) {
        V result = cacheMap.getOrDefault(key.toString(), null);
        for (HwListener<K, V> listener : this.listeners) {
            listener.notify(key, result, ACTION_CACHE_GET);
        }
        return result;
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
