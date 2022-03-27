package com.mmall.concurrency.geektime.concurrency.geek5;

import java.util.ArrayList;
import java.util.List;

/**
 * @author songyouyu
 * @date 2022/3/26
 */
public class Allocator {

    private List<Account> res = new ArrayList<>();

    public synchronized boolean apply(Account from, Account to) {
        if (res.contains(from) || res.contains(to)) {
            return false;
        }
        res.add(from);
        res.add(to);
        return true;
    }

    public synchronized void free(Account from, Account to) {
        res.remove(from);
        res.remove(to);
    }

}

class Account {

    /**
     * 单例
     */
    private Allocator allocator;
    private int balance;

    public void transfer(Account target, int bal) {
        while (!allocator.apply(this, target)) {}

        try {
            synchronized (this) {
                synchronized (target) {
                    if (balance >= bal) {
                        this.balance -= bal;
                        target.balance += bal;
                    }
                }
            }
        } finally {
            allocator.free(this, target);
        }
    }

}
