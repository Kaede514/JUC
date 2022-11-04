package com.kaede.pool;

import java.util.concurrent.*;

/**
 * @author kaede
 * @create 2022-09-10 13:12
 *
 * 自定义线程池的创建
 */

public class ThreadPoolDemo5 {

    public static void main(String[] args) {
        //传入参数
        ExecutorService threadPool = new ThreadPoolExecutor(
                2, 5, 10L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

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
