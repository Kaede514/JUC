package com.kaede.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author kaede
 * @create 2022-09-09 14:15
 */

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        //创建CountDownLatch对象，设置初始值
        CountDownLatch countDownLatch = new CountDownLatch(4);

        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " thread...");
                //计数减1
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        countDownLatch.await();

        //未使用CountDownLatch时可能会造成执行该代码时有线程未结束
        System.out.println("all thread over...");
    }

}
