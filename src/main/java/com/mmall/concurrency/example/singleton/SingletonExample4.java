package com.mmall.concurrency.example.singleton;

import com.mmall.concurrency.annoations.NotThreadSafe;

/**
 * 懒汉模式
 */
@NotThreadSafe
public class SingletonExample4 {

    // 私有构造函数
    private SingletonExample4() {

    }

    /*
     * 1.memory = allocate() 分配对象的内存空间
     * 2.ctorInstance() 初始化对象
     * 3.instance = memory 设置instance指向刚分配的内存
     */

    /*
     * jvm和cpu优化，进行了指令重排
     * 1.memory = allocate() 分配对象的内存空间
     * 3.instance = memory 设置instance指向刚分配的内存
     * 2.ctorInstance() 初始化对象
     */

    // 单例对象
    private static SingletonExample4 instance = null;

    // 静态工厂方法
    public static SingletonExample4 getInstance() {
        if (instance == null) {//双重检测机制
            synchronized (SingletonExample4.class) {//同步锁
                if (instance == null) {
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }
}
