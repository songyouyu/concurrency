package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by songyouyu on 2018/5/7
 */
@Slf4j
public class CountDownLatchExample2 {

    //测试时的线程数
    private static final int threadCount = 200;

    public static void main(String[] args) throws Exception{

        //线程池
        ExecutorService exec = Executors.newCachedThreadPool();

        //闭锁的实例
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i ++) {
            //Thread.sleep(1);
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    test(threadNum);
                } catch (Exception e) {
                    log.error("exception : {}", e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        //等待时间 后一个参数时单位
        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        log.info("finish");
        //等待所有线程执行完才销毁，并不是一旦调用这个方法，线程立即销毁
        exec.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(100);
        log.info("{}", threadNum);
    }
}
