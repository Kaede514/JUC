package com.kaede.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author kaede
 * @create 2022-09-13 10:36
 *
 * 实现一个自旋锁
 */

public class SpinLockDemo {

    private AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(() -> {
            spinLockDemo.lock();
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace();}
            spinLockDemo.unlock();
        },"A").start();

        //暂停500ms，保证线程A先启动
        try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace();}

        new Thread(() -> {
            spinLockDemo.lock();
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace();}
            spinLockDemo.unlock();
        },"B").start();
    }

    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " come in...");
        //若此时没有线程占有，则让当前线程占有
        while (!atomicReference.compareAndSet(null, thread)) {

        }
        System.out.println(Thread.currentThread().getName() + " lock...");
    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        //若此时被当前线程占有，则释放
        while (!atomicReference.compareAndSet(thread, null)) {

        }
        System.out.println(Thread.currentThread().getName() + " task over, unlock...");
    }

}
