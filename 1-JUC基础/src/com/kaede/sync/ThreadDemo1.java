package com.kaede.sync;

/**
 * @author kaede
 * @create 2022-09-08 17:08
 */

public class ThreadDemo1 {

    public static void main(String[] args) {
        Share share = new Share();
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
    }

}

class Share {
    private int number;

    public synchronized void increase() throws InterruptedException {
        if(number != 0) {
            this.wait();
        }
        this.number++;
        System.out.println(Thread.currentThread().getName() + "  number: " + this.number);
        //通知其他线程
        this.notifyAll();
    }

    public synchronized void decrease() throws InterruptedException {
        if(number != 1) {
            this.wait();
        }
        this.number--;
        System.out.println(Thread.currentThread().getName() + "  number: " + this.number);
        //通知其他线程
        this.notifyAll();
    }

}
