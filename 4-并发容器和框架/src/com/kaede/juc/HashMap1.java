package com.kaede.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author kaede
 * @create 2022-09-08 20:01
 *
 * HashMap线程不安全问题
 *
 */

public class HashMap1 {

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            String key = String.valueOf(i);
            new Thread(() -> {
                //向集合中添加内容，并且从集合中获取内容
                map.put(key,UUID.randomUUID().toString().substring(0,6));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

}
