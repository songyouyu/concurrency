package com.mmall.concurrency.geektime.concurrency.geek8;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 管程
 * @author songyouyu
 * @date 2022/3/27
 */
public class BlockedQueue<T> {

    ArrayBlockingQueue<T> queue = new ArrayBlockingQueue<>(1);

    final Lock lock = new ReentrantLock();

    /**
     * 队列不满
     */
    Condition notFull = lock.newCondition();

    /**
     * 队列不空
     */
    Condition notEmpty = lock.newCondition();

    public void enq(T t) {
        System.out.println("hello1");
        lock.lock();
        try {
            System.out.println("hello2");
            System.out.println("enq size : " + queue.size());
            while (queue.size() == 1) {
                System.out.println("hello3");
                notFull.await();
                System.out.println("hello4");
            }
            System.out.println("hello5");
            queue.put(t);
            System.out.println("enq size : " + queue.size());
            notEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public T deq() {
        lock.lock();
        try {
            System.out.println("deq size : " + queue.size());
            while (queue.isEmpty()) {
                notEmpty.await();
            }
            notFull.signalAll();
            T poll = queue.poll();
            System.out.println("deq size : " + queue.size());
            return poll;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return null;
    }

    public static void main(String[] args) {
        BlockedQueue<Integer> queue = new BlockedQueue<>();
        queue.enq(1);

        new Thread(() -> queue.enq(1)).start();

        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            queue.deq();
        }).start();
    }

}
