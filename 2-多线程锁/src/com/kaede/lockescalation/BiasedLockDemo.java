package com.kaede.lockescalation;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author kaede
 * @create 2022-09-15 14:33
 */

public class BiasedLockDemo {

    public static void main(String[] args) {
        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace();}
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o) {
            //此时为00，对应轻量级锁
            //关闭延迟或者启动4s后，为101，对应偏向锁
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
            //也可以通过在new对象之前sleep几秒等待偏向锁的开启
        }
    }

}
