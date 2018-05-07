package com.mmall.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by songyouyu on 2018/5/7
 */
@Slf4j
public class ThreadPoolExample2 {

    public static void main(String[] args) {

        //定长线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("task:{}", index);
                }
            });
        }
        executorService.shutdown();
    }
}
