package com.kaede.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author kaede
 * @create 2022-09-08 19:24
 *
 * ArrayList集合线程不安全的问题
 */

public class ArrayList1 {

    public static void main(String[] args) {
        //创建ArrayList
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                //向集合中添加内容，并且从集合中获取内容
                list.add(UUID.randomUUID().toString().substring(0,6));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

}
