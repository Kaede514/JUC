package com.kaede.interrupt;

/**
 * @author kaede
 * @create 2022-09-11 20:55
 */

public class InterruptDemo4 {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "  " + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + "  " + Thread.interrupted());
        System.out.println("--------- 1 ---------");
        Thread.currentThread().interrupt();
        System.out.println("--------- 2 ---------");
        System.out.println(Thread.currentThread().getName() + "  " + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + "  " + Thread.interrupted());
    }

}
