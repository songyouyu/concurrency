package com.mmall.concurrency.geektime.concurrency.geek18;

import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock(不支持重入，悲观读锁、写锁不支持条件变量，支持锁的降级和升级)
 * @author songyouyu
 * @date 2022/4/3 14:35
 */
public class Point {

    private int x, y;
    final StampedLock stampedLock = new StampedLock();

    /**
     * 计算到原点的距离
     * @return
     */
    double distanceFromOrigin() {
        // 乐观读，读的过程中数据可能被修改
        long optimisticReadLock = stampedLock.tryOptimisticRead();
        int curX = x, curY = y;
        // 验证数据是否被修改，如果被修改，升级为悲观读锁
        if (!stampedLock.validate(optimisticReadLock)) {
            optimisticReadLock = stampedLock.readLock();
            try {
                curX = x;
                curY = y;
            } finally {
                stampedLock.unlockRead(optimisticReadLock);
            }
        }
        return Math.sqrt(curX * curX + curY * curY);
    }


}
