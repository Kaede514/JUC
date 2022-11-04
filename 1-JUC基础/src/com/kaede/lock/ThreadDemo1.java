package com.kaede.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author kaede
 * @create 2022-09-08 17:08
 */

public class ThreadDemo1 {

    public static void main(String[] args) {
        Share share = new Share();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                share.increase();
            }
        }, "threadA").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                share.decrease();
            }
        }, "threadB").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                share.increase();
            }
        }, "threadC").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                share.decrease();
            }
        }, "threadD").start();
    }

}

class Share {
    private int number;

    //创建Lock
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increase() {
        try {
            lock.lock();
            while (number != 0) {
                condition.await();
            }
            this.number++;
            System.out.println(Thread.currentThread().getName() + "  number: " + this.number);
            //通知其他线程
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrease() {
        try {
            lock.lock();
            while (number != 1) {
                condition.await();
            }
            this.number--;
            System.out.println(Thread.currentThread().getName() + "  number: " + this.number);
            //通知其他线程
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
