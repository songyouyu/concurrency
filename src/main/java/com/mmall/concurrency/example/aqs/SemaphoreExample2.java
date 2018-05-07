package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by songyouyu on 2018/5/7
 */
@Slf4j
public class SemaphoreExample2 {

    //测试时的线程数
    private static final int threadCount = 20;

    public static void main(String[] args) throws Exception{

        //线程池
        ExecutorService exec = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadCount; i ++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    semaphore.acquire(3); //获取多个许可
                    test(threadNum);
                    semaphore.release(3); //释放多个许可
                } catch (Exception e) {
                    log.error("exception : {}", e);
                } finally {

                }
            });
        }
        exec.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        log.info("{}", threadNum);
        Thread.sleep(1000);
    }
}
