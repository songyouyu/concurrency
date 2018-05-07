package com.mmall.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by songyouyu on 2018/5/7
 */
@Slf4j
public class LockExample6 {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        // 从reentrantLock实例里获取了condition
        Condition condition = reentrantLock.newCondition();

        new Thread(() -> {
            try {
                // 线程1调用了lock方法，加入到了AQS的等待队列里面去
                reentrantLock.lock();
                log.info("wait signal"); // 1 等待信号
                // 调用await方法后，从AQS队列里移除了，进入到了condition队列里面去，等待一个信号
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("get signal"); // 4 得到信号
            // 线程1释放锁
            reentrantLock.unlock();
        }).start();

        new Thread(() -> {
            // 线程1await释放锁以后，这里就获取了锁，加入到了AQS等待队列中
            reentrantLock.lock();
            log.info("get lock"); // 2 获取锁
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //调用signalAll发送信号的方法,Condition节点的线程1节点元素被取出，放在了AQS等待队列里（注意并没有被唤醒）
            condition.signalAll();
            log.info("send signal ~ "); // 3 发送信号
            // 线程2释放锁，这时候AQS队列中只剩下线程1，线程1开始执行
            reentrantLock.unlock();
        }).start();
    }
}
