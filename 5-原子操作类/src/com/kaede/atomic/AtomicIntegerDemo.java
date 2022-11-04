package com.kaede.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author kaede
 * @create 2022-09-13 12:42
 */

public class AtomicIntegerDemo {

    private static final int SIZE = 50;

    public static void main(String[] args) throws InterruptedException {
        MyNumber myNumber = new MyNumber();
        CountDownLatch countDownLatch = new CountDownLatch(SIZE);

        for (int i = 0; i < SIZE; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 1000; j++) {
                        myNumber.add();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            },String.valueOf(i)).start();
        }

        //等待线程全部计算完成后再获得最终值，实际生产中不能这样
        //try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace();}

        //使用CountDownLatch保证全部计算完后立即放行
        countDownLatch.await();

        System.out.println("result = " + myNumber.atomicInteger.get());
    }

}

class MyNumber {
    public AtomicInteger atomicInteger = new AtomicInteger();

    public void add() {
        atomicInteger.getAndIncrement();
    }
}
