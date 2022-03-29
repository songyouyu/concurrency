package com.mmall.concurrency.geektime.concurrency.geek17;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author songyouyu
 * @date 2022/3/29
 */
public class Cache1<K, V> {

    final HashMap<K, V> map = new HashMap<>();
    final ReadWriteLock lock = new ReentrantReadWriteLock();

    final Lock r = lock.readLock();
    final Lock w = lock.writeLock();

    V get(K k) {
        r.lock();
        try {
            return map.get(k);
        } finally {
            r.unlock();
        }
    }

    void put(K k, V v) {
        w.lock();
        try {
            map.put(k, v);
        } finally {
            w.unlock();
        }
    }

}
