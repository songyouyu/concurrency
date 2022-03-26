package com.mmall.concurrency.geektime.concurrency.geek4;

/**
 * @author songyouyu
 * @date 2022/3/26
 */
public class Account1 {

    /*
    不同的锁锁住不同的资源。
    一个资源只有一把锁。
     */

    /**
     * 余额
     */
    private Integer balance;
    private final Object balLock = new Object();

    /**
     * 密码
     */
    private String password;
    private final Object pwdLock = new Object();

    void withdraw(Integer bal) {
        synchronized (balLock) {
            if (balance >= bal) {
                balance -= bal;
            }
        }
    }

    Integer getBalance() {
        synchronized (balLock) {
            return balance;
        }
    }

    void updatePassword(String pwd){
        synchronized (pwdLock) {
            password = pwd;
        }
    }

    String getPassword() {
        synchronized (pwdLock) {
            return password;
        }
    }

}
