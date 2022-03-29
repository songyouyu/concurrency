package com.mmall.concurrency.geektime.concurrency.geek16;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * 使用信号量实现对象池
 * @author songyouyu
 * @date 2022/3/29
 */
public class ObjPool<T, R> {

    final List<T> pool;

    final Semaphore semaphore;

    ObjPool(T t, int size) {
        pool = new Vector<>();
        for (int i = 0; i < size; i ++) {
            pool.add(t);
        }

        semaphore = new Semaphore(size);
    }

    R exec(Function<T, R> func) throws InterruptedException {
        T t = null;
        semaphore.acquire();
        try {
            t = pool.remove(0);
            return func.apply(t);
        } finally {
            pool.add(t);
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        ObjPool<Integer, String> pool = new ObjPool<>(10, 2);
        try {
            pool.exec(t -> {
                System.out.println(t);
                return t.toString();
            });
        } catch (InterruptedException e) {

        }
    }

}

