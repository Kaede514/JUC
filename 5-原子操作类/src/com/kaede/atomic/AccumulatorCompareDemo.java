package com.kaede.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author kaede
 * @create 2022-09-13 17:09
 *
 * 需求：50个线程，每个线程100W次，总点赞数
 */

public class AccumulatorCompareDemo {

    private static final int FREQUENCE = 1000000;
    private static final int THREAD_NUMBER = 50;

    public static void main(String[] args) throws InterruptedException {
        ClickNumber clickNumber = new ClickNumber();

        CountDownLatch countDownLatch1 = new CountDownLatch(50);
        CountDownLatch countDownLatch2 = new CountDownLatch(50);
        CountDownLatch countDownLatch3 = new CountDownLatch(50);
        CountDownLatch countDownLatch4 = new CountDownLatch(50);

        long startTime1 = System.currentTimeMillis();
        for (int i = 1; i <= THREAD_NUMBER; i++) {
             new Thread(() -> {
                try {
                    for (int j = 0; j < FREQUENCE; j++) {
                        clickNumber.clickBySync();
                    }
                } finally {
                    countDownLatch1.countDown();
                }
             }).start();
        }
        countDownLatch1.await();
        long endTime1 = System.currentTimeMillis();
        System.out.println("----- clickBySync cost time: " + (endTime1 - startTime1) + " ms -----");

        long startTime2 = System.currentTimeMillis();
        for (int i = 1; i <= THREAD_NUMBER; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < FREQUENCE; j++) {
                        clickNumber.clickByAtomicLong();
                    }
                } finally {
                    countDownLatch2.countDown();
                }
            }).start();
        }
        countDownLatch2.await();
        long endTime2 = System.currentTimeMillis();
        System.out.println("----- clickByAtomicLong cost time: " + (endTime2 - startTime2) + " ms -----");

        long startTime3 = System.currentTimeMillis();
        for (int i = 1; i <= THREAD_NUMBER; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < FREQUENCE; j++) {
                        clickNumber.clickByLongAdder();
                    }
                } finally {
                    countDownLatch3.countDown();
                }
            }).start();
        }
        countDownLatch3.await();
        long endTime3 = System.currentTimeMillis();
        System.out.println("----- clickByLongAdder cost time: " + (endTime3 - startTime3) + " ms -----");

        long startTime4 = System.currentTimeMillis();
        for (int i = 1; i <= THREAD_NUMBER; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < FREQUENCE; j++) {
                        clickNumber.clickByLongAccumulator();
                    }
                } finally {
                    countDownLatch4.countDown();
                }
            }).start();
        }
        countDownLatch4.await();
        long endTime4 = System.currentTimeMillis();
        System.out.println("----- clickByLongAccumulator cost time: " + (endTime4 - startTime4) + " ms -----");

        /*
            ----- clickBySync cost time: 6676 ms -----
            ----- clickByAtomicLong cost time: 1413 ms -----
            ----- clickByLongAdder cost time: 279 ms -----
            ----- clickByLongAccumulator cost time: 205 ms -----
        */
    }

}

class ClickNumber {
    private int number = 0;
    private AtomicLong atomicLong = new AtomicLong();
    private LongAdder longAdder = new LongAdder();
    private LongAccumulator longAccumulator = new LongAccumulator((x,y) -> x+y,0);

    public synchronized void clickBySync() {
        this.number++;
    }

    public void clickByAtomicLong() {
        this.atomicLong.getAndIncrement();
    }

    public void clickByLongAdder() {
        this.longAdder.increment();
    }

    public void clickByLongAccumulator() {
        this.longAccumulator.accumulate(1);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
