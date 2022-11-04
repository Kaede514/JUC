package com.kaede.lock_8;

/**
 * @author kaede
 * @create 2022-09-11 16:41
 */

public class LockSyncDemo {

    private Object o = new Object();

    public static void main(String[] args) {

    }

    public void m1() {
        synchronized (o) {
            System.out.println("----- synchronized code block -----");
        }
    }

    public synchronized void m2() {
        System.out.println("----- synchronized common method -----");
    }

    public static synchronized void m3() {
        System.out.println("----- synchronized static method -----");
    }

    public void m4() {
        synchronized (o) {
            System.out.println("----- synchronized code block -----");
            throw new RuntimeException();
        }
    }
}
