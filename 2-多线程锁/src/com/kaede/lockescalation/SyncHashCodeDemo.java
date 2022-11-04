package com.kaede.lockescalation;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author kaede
 * @create 2022-09-15 16:15
 */

public class SyncHashCodeDemo {

    public static void main(String[] args) {
        //m1();
        //m2();
        m3();
    }

    public static void m1() {
        //保证开启偏向锁
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object o = new Object();
        System.out.println("预计偏向锁");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        System.out.println(o.hashCode());
        synchronized (o) {
            //没有重写，一致性哈希，重写后无效，当一个对象已经计算过identity hashcode，
            //它就无法进入偏向锁状态
            System.out.println("预计轻量级锁");
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }

    public static void m2() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o) {
            System.out.println(o.hashCode());
            System.out.println("预计重量级锁");
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }

    public static void m3() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        System.out.println(o.hashCode());
        System.out.println("预计无锁");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }

}
