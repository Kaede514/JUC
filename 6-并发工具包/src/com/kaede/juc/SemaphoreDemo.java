package com.kaede.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author kaede
 * @create 2022-09-09 14:44
 */

public class SemaphoreDemo {

    //6辆汽车，停3个车位
    public static void main(String[] args) {
        //创建Semaphore对象，设置许可数量
        Semaphore semaphore = new Semaphore(3);

        //模拟6辆汽车
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    //抢占
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " 号车抢占了车位....");
                    //随机设置停车时间
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    //时间到了，车离开车位
                    System.out.println(Thread.currentThread().getName() + " 号车-----离开了车位...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //释放
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }

}
