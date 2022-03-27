package com.mmall.concurrency.geektime.concurrency.geek5;

/**
 * @author songyouyu
 * @date 2022/3/26
 */
public class Account2 {

    private int id;
    private int balance;

    public void transfer(Account2 target, int bal) {
        Account2 one = this;
        Account2 two = target;
        if (this.id > target.id) {
            one = target;
            two = this;
        }

        synchronized (one) {
            synchronized (two) {
                if (balance >= bal) {
                    this.balance -= bal;
                    target.balance += bal;
                }
            }
        }
    }

}
