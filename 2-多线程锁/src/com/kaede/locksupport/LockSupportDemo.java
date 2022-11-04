package com.kaede.locksupport;

import java.util.concurrent.TimeUnit;

/**
 * @author kaede
 * @create 2022-09-11 21:23
 */

public class LockSupportDemo {

    public static void main(String[] args) {
        //common();
        //exception1();
        exception2();
    }

    private static void exception2() {
        Object o = new Object();

        new Thread(() -> {
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace();}
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + " come in...");
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " is woken...");
            }
        },"t1").start();

        new Thread(() -> {
            synchronized (o) {
                o.notify();
                System.out.println(Thread.currentThread().getName() + " send out notifications...");
            }
        }, "t2").start();
    }

    private static void exception1() {
        Object o = new Object();

        new Thread(() -> {
            //synchronized (o) {
                System.out.println(Thread.currentThread().getName() + " come in...");
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " is woken...");
            //}
        },"t1").start();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace();}

        new Thread(() -> {
            //synchronized (o) {
                o.notify();
                System.out.println(Thread.currentThread().getName() + " send out notifications...");
            //}
        }, "t2").start();
    }

    private static void common() {
        Object o = new Object();

        new Thread(() -> {
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + " come in...");
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " is woken...");
            }
        },"t1").start();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace();}

        new Thread(() -> {
            synchronized (o) {
                o.notify();
                System.out.println(Thread.currentThread().getName() + " send out notifications...");
            }
        }, "t2").start();
    }

}
