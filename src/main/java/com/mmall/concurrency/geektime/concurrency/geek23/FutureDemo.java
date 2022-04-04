package com.mmall.concurrency.geektime.concurrency.geek23;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author songyouyu
 * @date 2022/4/4 14:16
 */
public class FutureDemo {

    public void test() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Result result = new Result();
        result.setA(2);
        // result 为主线程和子线程之间的桥梁，通过它主子线程可以共享数据。
        Future<Result> future = executor.submit(new Task(result), result);
        Result r = future.get();

        // r === result
        // r.getA() === 2
    }

}

class Task implements Runnable {

    private Result result;

    Task(Result result) {
        this.result = result;
    }

    @Override
    public void run() {
        // 此处可以操作 result
        int a = result.getA();
    }
}


class Result {

    private int a;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}