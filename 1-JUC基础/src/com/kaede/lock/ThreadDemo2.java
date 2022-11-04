package com.kaede.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author kaede
 * @create 2022-09-08 19:05
 *
 * 线程间定制化通信
 *
 */

public class ThreadDemo2 {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                try {
                    shareResource.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "threadA").start();
        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                try {
                    shareResource.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "threadB").start();
        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                try {
                    shareResource.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "threadC").start();
    }

}

class ShareResource {
    //定义标志位
    private int flag = 1; //1 threadA  2 threadB  3 threadC

    //创建Lock
    private Lock lock = new ReentrantLock();
    //创建三个Condition
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5(int loop) throws InterruptedException {
        try {
            this.lock.lock();
            while(flag != 1) {
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "  loop: " + loop);
            //通知
            this.flag = 2;  //修改标志位
            condition2.signal();    //通知threadB
        } finally {
            this.lock.unlock();
        }
    }

    public void print10(int loop) throws InterruptedException {
        try {
            this.lock.lock();
            while(flag != 2) {
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + "  loop: " + loop);
            //通知
            this.flag = 3;  //修改标志位
            condition3.signal();    //通知threadC
        } finally {
            this.lock.unlock();
        }
    }
    public void print15(int loop) throws InterruptedException {
        try {
            this.lock.lock();
            while(flag != 3) {
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + "  loop: " + loop);
            //通知
            this.flag = 1;  //修改标志位
            condition1.signal();    //通知threadA
        } finally {
            this.lock.unlock();
        }
    }

}
