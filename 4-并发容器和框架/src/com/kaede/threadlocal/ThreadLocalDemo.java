package com.kaede.threadlocal;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author kaede
 * @create 2022-09-14 13:35
 *
 */

public class ThreadLocalDemo {

    public static void main(String[] args) throws InterruptedException {
        House house = new House();
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 1; i <= 5; i++) {
            new Thread(()    -> {
                try {
                    int size = new Random().nextInt(5) + 1;
                    System.out.println(Thread.currentThread().getName() + " sale house: " + size);
                    for (int j = 0; j < size; j++) {
                        house.saleHouse();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            },String.valueOf(i)).start();
        }

        countDownLatch.await();
        System.out.println("house sale count = " + house.getSaleCount());

        House house1 = new House();
        CountDownLatch countDownLatch1 = new CountDownLatch(5);

        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                try {
                    int size = new Random().nextInt(5) + 1;
                    for (int j = 0; j < size; j++) {
                        house1.saleHouse();
                        house1.saleVolumeByThreadLocal();
                    }
                    System.out.println(Thread.currentThread().getName() + " sale number = "
                        + house1.getSaleVolume().get());
                } finally {
                    countDownLatch1.countDown();
                    //清理
                    house1.getSaleVolume().remove();
                }
            },String.valueOf(i)).start();
        }

        countDownLatch1.await();
        System.out.println("house1 sale count = " + house1.getSaleCount());
    }

}

class House {
    private int saleCount;

    public synchronized void saleHouse() {
        this.saleCount++;
    }

    /*ThreadLocal<Integer> saleVolume = new ThreadLocal<>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };*/

    private ThreadLocal<Integer> saleVolume = ThreadLocal.withInitial(() -> 0);

    public void saleVolumeByThreadLocal() {
        this.saleVolume.set(this.saleVolume.get()+1);
    }

    public ThreadLocal<Integer> getSaleVolume() {
        return saleVolume;
    }

    public void setSaleVolume(ThreadLocal<Integer> saleVolume) {
        this.saleVolume = saleVolume;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }
}
