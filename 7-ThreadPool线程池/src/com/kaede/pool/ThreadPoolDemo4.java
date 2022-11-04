package com.kaede.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author kaede
 * @create 2022-09-09 20:25
 */

public class ThreadPoolDemo4 {

    public static void main(String[] args) {
        //一池可扩容线程
        ExecutorService threadPool = Executors.newScheduledThreadPool(5);

        try {
            //10个顾客请求
            for (int i = 1; i <= 10; i++) {
                //执行
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " 线程正在办理业务...");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭
            threadPool.shutdown();
        }
    }

}
