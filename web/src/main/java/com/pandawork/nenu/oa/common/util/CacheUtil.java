package com.pandawork.nenu.oa.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * CacheUtil
 *
 * @author wlm
 * @date 2016/7/24 8:48
 */
public class CacheUtil{

    private Map<String, Object> Cache = new ConcurrentHashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        Cache.put(key, value);
    }

    public Object get(String key) {
        return Cache.get(key);
    }

    public Object remove(String key) {
        return Cache.remove(key);
    }

    public Object replace(String key, Object value) {
        return Cache.replace(key, value);
    }

    public Collection<Map.Entry> getAll() {
        return new ArrayList<>(Cache.entrySet());
    }

    public void clear() {
        Cache.clear();
    }

    public int getSize() {
        return Cache.size();
    }

    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }
}
