package com.kaede.juc;

/**
 * @author kaede
 * @create 2022-10-30
 */

public class ThreadTest {

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start();
        Thread t2 = new Thread(new MyThread2());
        t2.start();
        //方式二(2)
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                if (i % 2 == 0) {
                    System.out.println("t3--" + i);
                }
            }
        });
        t3.start();
        System.out.println(Thread.currentThread().getName());
    }

}

//方式一
class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0) {
                System.out.println("t1--" + i);
            }
        }
    }
}

//方式二(1)
class MyThread2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0) {
                System.out.println("t2--" + i);
            }
        }
    }
}
