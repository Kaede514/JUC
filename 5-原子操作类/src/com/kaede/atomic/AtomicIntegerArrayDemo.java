package com.kaede.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author kaede
 * @create 2022-09-13 13:01
 */

public class AtomicIntegerArrayDemo {

    public static void main(String[] args) {
        AtomicIntegerArray atomicIntegerArray1 = new AtomicIntegerArray(5);
        AtomicIntegerArray atomicIntegerArray2 = new AtomicIntegerArray(new int[5]);
        AtomicIntegerArray atomicIntegerArray3 = new AtomicIntegerArray(new int[]{1, 2, 3, 4, 5});

        for (int i = 0; i < atomicIntegerArray3.length(); i++) {
            System.out.println(atomicIntegerArray3.get(i));
        }

        int tempInt = 0;
        tempInt = atomicIntegerArray3.getAndSet(0,2022);
        System.out.println("tempInt = " + tempInt + "  value = " + atomicIntegerArray3.get(0));

        tempInt = atomicIntegerArray3.getAndIncrement(0);
        System.out.println("tempInt = " + tempInt + "  value = " + atomicIntegerArray3.get(0));
    }

}
