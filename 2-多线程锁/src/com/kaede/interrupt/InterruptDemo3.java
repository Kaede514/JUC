package com.kaede.interrupt;

/**
 * @author kaede
 * @create 2022-09-11 20:35
 */

public class InterruptDemo3 {

    public static void main(String[] args) {
        //m1();
        //m2();
        m3();
    }

    private static void m1() {
        Thread t1 = new Thread(() -> {
            while (true) {
                System.out.println("----- " + Thread.currentThread().getName() + " -----");
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + " end...");
                    break;
                }
            }
        }, "t1");
        t1.start();

        new Thread(() -> {
            t1.interrupt();
        },"t2").start();
    }

    private static void m2() {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + " end...");
                    break;
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    //这样会抛异常，然后继续执行while循环，解决方法如下，再设置一次中断标志位
                    //Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
                System.out.println("----- " + Thread.currentThread().getName() + " -----");
            }
        }, "t3");
        t1.start();

        new Thread(() -> {
            t1.interrupt();
        },"t4").start();
    }

    private static void m3() {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + " end...");
                    break;
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    //这样会抛异常，然后继续执行while循环，解决方法如下，再设置一次中断标志位
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
                System.out.println("----- " + Thread.currentThread().getName() + " -----");
            }
        }, "t3");
        t1.start();
        new Thread(() -> {
            t1.interrupt();
        },"t4").start();
    }

}
