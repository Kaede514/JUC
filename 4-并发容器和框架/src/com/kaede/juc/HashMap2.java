package com.kaede.juc;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author kaede
 * @create 2022-09-08 20:01
 */

public class HashMap2 {

    public static void main(String[] args) {
        Map<String,String> map = new ConcurrentHashMap<>();
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
