package com.mmall.concurrency.example.syncContainer;

import java.util.Iterator;
import java.util.Vector;

/**
 * Created by songyouyu on 2018/5/6
 */

public class VectorExample3 {

    // java.util.ConcurrentModificationException
    // 在遍历的同事进行删除操作，会抛出异常
    private static void test1(Vector<Integer> v1) { // foreach
        for (Integer i : v1) {
            if (i.equals(3)) {
                v1.remove(i);
            }
        }
    }

    // java.util.ConcurrentModificationException
    private static void test2(Vector<Integer> v2) { // iterator
        Iterator<Integer> iterator = v2.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i.equals(3)) {
                v2.remove(i);
            }
        }
    }

    // no exception
    private static void test3(Vector<Integer> v3) { // for
        for (int i = 0; i < v3.size(); i ++) {
            if (v3.get(i).equals(i)) {
                v3.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);

        test1(vector);
        //test2(vector);
        //test3(vector);
    }
}
