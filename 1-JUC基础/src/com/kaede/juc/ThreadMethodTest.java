package com.kaede.juc;

/**
 * @author kaede
 * @create 2022-10-30
 */

public class ThreadMethodTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                if(i % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }
                if(i == 20) {
                    Thread.yield();
                }
            }
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                if(i % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }
            }
        },"t2");
        t2.start();
        //给主线程命名
        Thread.currentThread().setName("主线程");
        System.out.println(Thread.currentThread().getName());
    }

}

