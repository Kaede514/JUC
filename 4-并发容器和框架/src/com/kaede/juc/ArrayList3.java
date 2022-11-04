package com.kaede.juc;

import java.util.*;

/**
 * @author kaede
 * @create 2022-09-08 19:24
 */

public class ArrayList3 {

    public static void main(String[] args) {
        //通过Collections里的方法
        List<String> list = Collections.synchronizedList(new ArrayList<String>());
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                //向集合中添加内容，并且从集合中获取内容
                list.add(UUID.randomUUID().toString().substring(0,6));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

}
