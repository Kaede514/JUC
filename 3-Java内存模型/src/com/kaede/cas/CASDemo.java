package com.kaede.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author kaede
 * @create 2022-09-12 21:31
 */

public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        //底层调用的是compareAndSwap
        System.out.println(atomicInteger.compareAndSet(5, 2022) + "  " + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 2022) + "  " + atomicInteger.get());
    }

}
