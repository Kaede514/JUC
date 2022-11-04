package com.kaede.lockescalation;

/**
 * @author kaede
 * @create 2022-09-15 16:55
 */

public class LockBigDemo {

    static Object oLock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (oLock) {
                System.out.println("111...");
            }
            synchronized (oLock) {
                System.out.println("222...");
            }
            synchronized (oLock) {
                System.out.println("333...");
            }

            //相当于以下
            //锁粗化，若方法中首尾相接，前后相邻的都是同一个锁对象，则JIT编译器会把这几个
            //synchronized块合并成一个大块，加粗加大范围，一次申请锁使用即可，避免次次的
            //申请和释放锁，提高了性能
            synchronized (oLock) {
                System.out.println("111...");
                System.out.println("222...");
                System.out.println("333...");
            }
        }).start();
    }

}
