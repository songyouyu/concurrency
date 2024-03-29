package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annoations.ThreadSafe;

/**
 * 饿汉模式
 */
@ThreadSafe
public class SingletonExample6 {

    // 私有构造函数
    private SingletonExample6() {

    }

    // 单例对象
    private static SingletonExample6 instance = null;

    static {
        instance = new SingletonExample6();
    }

    // 静态工厂方法
    public static SingletonExample6 getInstance() {
        return instance;
    }
}
