package com.mmall.concurrency.geektime.concurrency.geek26;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 统计单词的个数
 * @author songyouyu
 * @date 2022/4/6 21:55
 */
public class MRDemo {

    public static void main(String[] args) {
        String[] fc = {"hello world", "hello me", "hello fork", "hello join", "fork join in world"};

        ForkJoinPool pool = new ForkJoinPool(3);
        MR mr = new MR(fc, 0, fc.length);
        Map<String, Long> res = pool.invoke(mr);
        res.forEach((k, v) -> System.out.println(k + ":" + v));
    }

}

class MR extends RecursiveTask<Map<String, Long>> {

    private final String[] fc;
    private final int start;
    private final int end;

    MR(String[] fc, int start, int end) {
        this.fc = fc;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Map<String, Long> compute() {
        if (end - start == 1) {
            return cal(fc[start]);
        } else {
            int mid = (end + start) / 2;
            MR m1 = new MR(fc, start, mid);
            m1.fork();
            MR m2 = new MR(fc, mid, end);

            return merge(m2.compute(), m1.join());
        }
    }

    private Map<String, Long> merge(Map<String, Long> map1, Map<String, Long> map2) {
        Map<String, Long> resMap = new HashMap<>(map1);
        map2.forEach((k, v) -> resMap.merge(k, v, Long::sum));
        return resMap;
    }

    private Map<String, Long> cal(String line) {
        Map<String, Long> res = new HashMap<>();
        String[] str = line.split(" ");
        for (String s : str) {
            Long v = res.get(s);
            if (v != null) {
                res.put(s, v + 1);
            } else {
                res.put(s, 1L);
            }
        }

        return res;
    }

}