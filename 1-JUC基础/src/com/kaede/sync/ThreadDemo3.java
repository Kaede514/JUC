package com.kaede.sync;

/**
 * @author kaede
 * @create 2022-10-31
 */

public class ThreadDemo3 {

    public static void main(String[] args) {
        Share1 share = new Share1();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "threadA").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decrease();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "threadB").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "threadC").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decrease();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "threadD").start();
    }
}

class Share1 {
    private int number;

    public synchronized void increase() throws InterruptedException {
        while(number != 0) {
            this.wait();
        }
        this.number++;
        System.out.println(Thread.currentThread().getName() + "  number: " + this.number);
        //通知其他线程
        this.notifyAll();
    }

    public synchronized void decrease() throws InterruptedException {
        while(number != 1) {
            this.wait();
        }
        this.number--;
        System.out.println(Thread.currentThread().getName() + "  number: " + this.number);
        //通知其他线程
        this.notifyAll();
    }

}
