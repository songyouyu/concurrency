package com.mmall.concurrency.geektime.concurrency.geek4;

/**
 * @author songyouyu
 * @date 2022/3/26
 */
public class Account2 {

    /*
    同一把锁锁不不同的资源。
     */

    private Integer balance;
    private Object lock;

    private Account2(){}

    /**
     * 创建 Account 对象的时候必须传入同一个对象
     * @param lock
     */
    public Account2(Object lock) {
        this.lock = lock;
    }

    public void transfer(Account2 target, Integer bal) {
        synchronized (lock) {
            if (this.balance >= bal) {
                this.balance -= bal;
                target.balance += bal;
            }
        }
    }

    public void transfer2(Account2 target, Integer bal) {
        synchronized (Account2.class) {
            if (this.balance >= bal) {
                this.balance -= bal;
                target.balance += bal;
            }
        }
    }

}
