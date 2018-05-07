package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by songyouyu on 2018/5/7
 */
@Slf4j
public class FutureTaskExample {

    public static void main(String[] args) throws Exception{
        FutureTask<String> task = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("do something in callable");
                Thread.sleep(5000);
                return "OK";
            }
        });

        new Thread(task).start();
        log.info("do something in maain");
        Thread.sleep(1000);
        String res = task.get();
        log.info("result : {}", res);
    }
}
