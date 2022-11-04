package com.kaede.juc;

import java.util.concurrent.CyclicBarrier;

/**
 * @author kaede
 * @create 2022-09-09 14:30
 */

public class CyclicBarrierDemo {

    private static final int NUMBER = 3;

    public static void main(String[] args) {
        //创建CyclicBarrier
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER, () -> {
            System.out.println("***集齐3颗龙珠就可以召唤神龙***");
        });

        //集齐6颗龙珠的过程
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 星龙珠被收集到了...");
                //等待
                try {
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + "等待结束");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }

}
