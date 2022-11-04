package com.kaede.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author kaede
 * @create 2022-09-11 21:37
 */

public class LockSupportDemo2 {

    public static void main(String[] args) {
        //common();
        //exception1();
        exception2();
    }

    private static void exception2() {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace();}
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " come in...");
                condition.await();
                System.out.println(Thread.currentThread().getName() + " is woken...");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + " send out notifications...");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"t2").start();
    }

    private static void exception1() {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            //lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " come in...");
                condition.await();
                System.out.println(Thread.currentThread().getName() + " is woken...");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //lock.unlock();
            }
        },"t1").start();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace();}

        new Thread(() -> {
            //lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + " send out notifications...");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //lock.unlock();
            }
        },"t2").start();
    }

    private static void common() {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " come in...");
                condition.await();
                System.out.println(Thread.currentThread().getName() + " is woken...");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"t1").start();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace();}

        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + " send out notifications...");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"t2").start();
    }

}
