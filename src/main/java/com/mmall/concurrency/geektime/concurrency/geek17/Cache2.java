package com.mmall.concurrency.geektime.concurrency.geek17;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * @author songyouyu
 * @date 2022/3/29
 */
public class Cache2<K, V> {

    final HashMap<K, V> map = new HashMap<>();
    final ReadWriteLock lock = new ReentrantReadWriteLock();

    final Lock r = lock.readLock();
    final Lock w = lock.writeLock();

    V get(K k) {
        V v = null;
        r.lock();
        try {
            v = map.get(k);
        } finally {
            r.unlock();
        }

        if (v != null) {
            return v;
        }

        w.lock();
        try {
            v = map.get(k);
            if (v == null) {
                // 查询数据库等操作
                // v = ...
                map.put(k, v);
            }
        } finally {
            w.unlock();
        }

        return v;
    }

}
