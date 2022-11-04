package com.kaede.lockescalation;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author kaede
 * @create 2022-09-15 15:39
 */

public class LightweightLockDemo {

    public static void main(String[] args) {
        //-XX:-UseBiasedLocking关闭偏向锁，直接进入轻量级锁
        //不关闭目前也没事，偏向锁的开启有4s延迟
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        new Thread(() -> {
            synchronized (o) {
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        }).start();
    }

}
