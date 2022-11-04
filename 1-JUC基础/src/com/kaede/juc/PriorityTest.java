package com.kaede.juc;

/**
 * @author kaede
 * @create 2022-10-31
 */

public class PriorityTest {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("t1-priority:" + Thread.currentThread().getPriority());
            for (int i = 0; i < 10; i++) {
                if(i % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }
            }
        },"t1");
        t1.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        System.out.println("main-priority:" + Thread.currentThread().getPriority());
        for (int i = 0; i < 10; i++) {
            if(i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }

}
