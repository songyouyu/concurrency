package com.mmall.concurrency.geektime.concurrency.geek19;

import java.util.Vector;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier.一组线程之间互相等待。
 * @author songyouyu
 * @date 2022/4/4 11:07
 */
public class CheckOrder2<P, D> {

    // 派送单队列
    Vector<P> pos;
    // 对账单队列
    Vector<D> dos;

    Executor executor = Executors.newSingleThreadExecutor();
    final CyclicBarrier cb = new CyclicBarrier(2, () -> {
        executor.execute(() -> check());
    });

    public void checkOrder() {
        Thread thread1 = new Thread(() -> {
            // pos.add();
            try {
                cb.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        thread1.start();

        Thread thread2 = new Thread(() -> {
            // dos.add();
            try {
                cb.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 执行对账
     */
    public void check() {
        P p = pos.remove(0);
        D d = dos.remove(0);

        // check(p, d);
    }

}
