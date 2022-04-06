package com.mmall.concurrency.geektime.concurrency.geek26;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author songyouyu
 * @date 2022/4/6 21:30
 */
public class FibonacciDemo {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(4);
        Fibonacci f = new Fibonacci(30);
        Integer res = pool.invoke(f);
        System.out.println(res);
    }

}

class Fibonacci extends RecursiveTask<Integer> {

    private final int n;

    Fibonacci(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        }
        Fibonacci f1 = new Fibonacci(n - 1);
        f1.fork();

        Fibonacci f2 = new Fibonacci(n - 2);
        return f2.compute() + f1.join();
    }

}