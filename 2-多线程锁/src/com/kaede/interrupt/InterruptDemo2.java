package com.kaede.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author kaede
 * @create 2022-09-11 20:17
 */

public class InterruptDemo2 {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("----- i = " + i + " -----");
            }
            System.out.println("inner t1 is interrupt: " + Thread.currentThread().isInterrupted());
        }, "t1");
        t1.start();

        new Thread(() -> {
            t1.interrupt();
            System.out.println("t2 -> t1 is interrupt: " + t1.isInterrupted());
        },"t2").start();

        new Thread(() -> {
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace();}
            //此时t1已经执行完了，中断不活动的线程不会产生任何影响，故为false
            t1.interrupt();
            System.out.println("t3 -> t1 is interrupt: " + t1.isInterrupted());
        },"t3").start();
    }

}
