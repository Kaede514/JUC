package com.kaede.juc;

import java.util.List;
import java.util.UUID;
import java.util.Vector;

/**
 * @author kaede
 * @create 2022-09-08 19:24
 */

public class ArrayList2 {

    public static void main(String[] args) {
        //创建Vecotr
        //Vector的add()方法上加了synchronized关键字
        List<String> list = new Vector<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                //向集合中添加内容，并且从集合中获取内容
                list.add(UUID.randomUUID().toString().substring(0,6));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

}
