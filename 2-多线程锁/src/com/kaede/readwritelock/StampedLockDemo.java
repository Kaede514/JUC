package com.kaede.readwritelock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @author kaede
 * @create 2022-09-16 13:00
 *
 * StampedLock = ReentrantReadWriteLock + 读的过程允许获取写锁
 */

public class StampedLockDemo {

    private static int number = 0;
    private static StampedLock stampedLock = new StampedLock();

    public static void main(String[] args) throws InterruptedException {
        StampedLockDemo resource = new StampedLockDemo();
        CountDownLatch countDownLatch = new CountDownLatch(2);

        /*情况1：传统版
        new Thread(() -> {
            resource.read();
            countDownLatch.countDown();
        },"readThread").start();

        try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace();}

        new Thread(() -> {
            resource.write();
            countDownLatch.countDown();
        },"writeThread").start();*/


        /*情况2：读的时候未获取写锁
        new Thread(() -> {
            resource.tryOptimisticRead();
            countDownLatch.countDown();
        },"readThread").start();

        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace();}

        new Thread(() -> {
            resource.write();
            countDownLatch.countDown();
        },"writeThread").start();*/


        //情况3：读的时候获取写锁
        new Thread(() -> {
            resource.tryOptimisticRead();
            countDownLatch.countDown();
        },"readThread").start();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace();}

        new Thread(() -> {
            resource.write();
            countDownLatch.countDown();
        },"writeThread").start();


        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + " 读取结果为 " + number);
    }

    public void write() {
        long stamp = stampedLock.writeLock();
        System.out.println(Thread.currentThread().getName() + " 写线程准备修改...");
        try {
            this.number++;
        } finally {
            stampedLock.unlockWrite(stamp);
        }
        System.out.println(Thread.currentThread().getName() + " 写线程结束修改...");
    }

    //悲观读，读未完成时无法获取写锁
    public void read() {
        long stamp = stampedLock.readLock();
        System.out.println(Thread.currentThread().getName() + " 读线程准备读...");
        for (int i = 1; i <= 3; i++) {
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace();}
            System.out.println(Thread.currentThread().getName() + " 正在读取中...");
        }
        try {
            System.out.println(Thread.currentThread().getName() + " 读取结果为 " + number);
        } finally {
            stampedLock.unlockRead(stamp);
        }
    }

    //乐观读，读未完成时可以获取写锁
    public void tryOptimisticRead() {
        long stamp = stampedLock.tryOptimisticRead();
        System.out.println(Thread.currentThread().getName() + " 读线程准备读...");
        System.out.println("3s前validate方法值（true无修改，false有修改）：" + stampedLock.validate(stamp));
        for (int i = 1; i <= 3; i++) {
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace();}
            System.out.println(Thread.currentThread().getName() + " 读取结果为 " + number);
            System.out.println(i + "s时validate方法值（true无修改，false有修改）：" + stampedLock.validate(stamp));
        }
        if(!stampedLock.validate(stamp)) {
            System.out.println("已被修改...");
            stamp = stampedLock.readLock();
            try {
                System.out.println("从乐观读升级为悲观读...");
                System.out.println(Thread.currentThread().getName() + " 重新悲观读后结果为 " + number);
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
        System.out.println(Thread.currentThread().getName() + " 最终读取结果为 " + number);
    }

}
