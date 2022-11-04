package com.kaede.jmm;

import java.util.concurrent.TimeUnit;

/**
 * @author kaede
 * @create 2022-09-12 17:44
 */

public class VolatileSeeDemo {

    public static void main(String[] args) {
        //不加volatile，没有可见性，程序无法停止
        //m1();

        //加了volatile，保证可见性，程序可以停止
        m2();
    }

    private static boolean flag = true;

    private static void m1() {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " come in...");
            while(flag) {

            }
            System.out.println(Thread.currentThread().getName() + " over, flag = flase...");
        },"t1").start();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace();}

        flag = false;
        System.out.println(Thread.currentThread().getName() + " updated...");
    }

    private static volatile boolean flag2 = true;

    private static void m2() {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " come in...");
            while(flag2) {

            }
            System.out.println(Thread.currentThread().getName() + " over, flag = flase...");
        },"t1").start();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace();}

        flag2 = false;
        System.out.println(Thread.currentThread().getName() + " updated...");
    }

}
