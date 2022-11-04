package com.kaede.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author kaede
 * @create 2022-09-12 10:32
 */

public class LockSupportDemo3 {

    public static void main(String[] args) {
        //m1();
        //m2();
        m3();
    }

    private static void m3() {
        //一张permit只能使用一次，并且最多只能持有一张permit
        Thread t1 = new Thread(() -> {
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace();}
            System.out.println(Thread.currentThread().getName() + " come in...");
            LockSupport.park();
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + " is woken...");
        }, "t1");
        t1.start();

        new Thread(() -> {
            LockSupport.unpark(t1);
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + " send out notifications...");
        },"t2").start();
    }

    private static void m2() {
        Thread t1 = new Thread(() -> {
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace();}
            System.out.println(Thread.currentThread().getName() + " come in...");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + " is woken...");
        }, "t1");
        t1.start();

        new Thread(() -> {
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + " send out notifications...");
        },"t2").start();
    }

    private static void m1() {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " come in...");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + " is woken...");
        }, "t1");
        t1.start();

        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace();}

        new Thread(() -> {
            LockSupport.unpark(t1);
            System.out.println(Thread.currentThread().getName() + " send out notifications...");
        },"t2").start();
    }

}
