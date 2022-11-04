package com.kaede.lockescalation;

/**
 * @author kaede
 * @create 2022-09-15 16:46
 */

public class LockClearDemo {

    static Object oLock = new Object();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                m1();
            }).start();
        }
    }

    public static void m1() {
        //这种情况下肯定会加锁
        /*synchronized (oLock) {
            System.out.println("----- hello lock clear -----");
        }*/

        //锁消除，JIT编译器会无视它，这个锁对象并没有被共用扩散到其他线程
        //使用，极端的说没有加这个锁对象的底层机器码，消除了锁的使用
        Object o = new Object();
        synchronized (o) {
            System.out.println("----- hello lock clear: " + o.hashCode() + "\t" + oLock.hashCode());
        }
    }

}
