package com.kaede.interrupt;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author kaede
 * @create 2022-09-11 19:11
 */

public class InterruptDemo {
    private static volatile boolean isStop = false;
    private static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) {
        //m1();
        //m2();
        m3();
    }

    private static void m3() {
        Thread thread = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + " end...");
                    break;
                }
                System.out.println("----- threadAPI -----");
            }
        }, "t_threadAPI");
        thread.start();

        new Thread(() -> {
            System.out.println("t_threadAPI的默认中断标识：" + thread.isInterrupted());
            thread.interrupt();
        },"t3").start();
    }

    private static void m2() {
        new Thread(() -> {
            while(true) {
                if(atomicBoolean.get()) {
                    System.out.println(Thread.currentThread().getName() + " end...");
                    break;
                }
                System.out.println("----- atomicBoolean -----");
            }
        },"t_atomicBoolean").start();

        new Thread(() -> {
            atomicBoolean.set(true);
        },"t2").start();
    }

    private static void m1() {
        new Thread(() -> {
            while(true) {
                if(isStop) {
                    System.out.println(Thread.currentThread().getName() + " end...");
                    break;
                }
                System.out.println("----- volatile -----");
            }
        },"t_volatile").start();

        new Thread(() -> {
            isStop = true;
        },"t1").start();
    }

}
