package com.kaede.juc;

import java.util.*;

/**
 * @author kaede
 * @create 2022-09-08 20:01
 *
 * HashSet线程不安全问题
 *
 */

public class HashSet1 {

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                //向集合中添加内容，并且从集合中获取内容
                set.add(UUID.randomUUID().toString().substring(0,6));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

}
