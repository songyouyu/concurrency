package com.mmall.concurrency.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by songyouyu on 2018/5/6
 */
@Slf4j
@ThreadSafe
public class ImmutableExample3 {

    private final static ImmutableList list = ImmutableList.of(1, 2, 3);

    private final static ImmutableSet set = ImmutableSet.copyOf(list);

    private final static ImmutableMap<Integer, Integer> map1 = ImmutableMap.of(1, 2, 3, 4);

    private final static ImmutableMap<Integer, Integer> map2 = ImmutableMap.<Integer, Integer>builder().put(1,2 ).
            put(3, 4).build();

    public static void main(String[] args) {
        //list.add(4);
        log.info("{}", map1.get(3));
    }
}
