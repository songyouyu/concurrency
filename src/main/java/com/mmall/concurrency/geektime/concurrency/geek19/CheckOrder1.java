package com.mmall.concurrency.geektime.concurrency.geek19;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * CountDownLatch。一个线程等待多个线程
 * @author songyouyu
 * @date 2022/4/4 10:57
 */
public class CheckOrder1 {

    Executor executors = Executors.newFixedThreadPool(2);
    final CountDownLatch countDownLatch = new CountDownLatch(2);

    public void checkOrder() throws InterruptedException {
        executors.execute(() -> {
            // 查找未对帐订单...

            countDownLatch.countDown();
        });

        executors.execute(() -> {
            // 查找派送单...

            countDownLatch.countDown();
        });

        countDownLatch.await();
        // check(); 执行对账操作
    }

}
