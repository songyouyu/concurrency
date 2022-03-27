package com.mmall.concurrency.geektime.concurrency.geek5;

/**
 *
 * 死锁相关
 * @author songyouyu
 * @date 2022/3/26
 */
public class Account1 {

    private int balance;

    public void transfer(Account1 target, int bal) {
        synchronized (this) {
            synchronized (target) {
                if (balance >= bal) {
                    this.balance -= bal;
                    target.balance += bal;
                }
            }
        }
    }

}
