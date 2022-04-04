package com.mmall.concurrency.geektime.concurrency.geek25;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * CompletionService. 批量执行异步任务
 * @author songyouyu
 * @date 2022/4/4 15:42
 */
public class ForkingCluster {

    public Object forkCluster() throws InterruptedException, ExecutionException {
        Executor executor = Executors.newFixedThreadPool(3);
        CompletionService<Object> cs = new ExecutorCompletionService<>(executor);

        List<Future<Object>> list = new ArrayList<>();
        list.add(cs.submit(this::geocoderByS1));
        list.add(cs.submit(this::geocoderByS2));
        list.add(cs.submit(this::geocoderByS3));

        Object o = null;
        try {
            for (int i = 0; i < 3; i ++) {
                o = cs.take().get();
                if (o != null) {
                    break;
                }
            }
        } finally {
            for (Future<Object> f : list) {
                f.cancel(true);
            }
        }

        return o;
    }

    public String  geocoderByS1() {
        return "";
    }
    public String  geocoderByS2() {
        return "";
    }
    public String  geocoderByS3() {
        return "";
    }

}
