package com.kaede.juc;

/**
 * @author kaede
 * @create 2022-10-31
 */

public class ThreadMethodTest2 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                if(i % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");
        t1.start();
        System.out.println("t1.isAlive() = " + t1.isAlive());
        t1.join();
        System.out.println("t1.isAlive() = " + t1.isAlive());
    }

}
