package com.kaede.futuretask;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @author kaede
 * @create 2022-09-10 16:57
 */

public class FutureThreadPoolDemo {

    @Test
    public void m1() {
        //3个任务，目前只有一个线程main来处理
        long startTime = System.currentTimeMillis();
        try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace();}
        try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace();}
        try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace();}
        long endTime = System.currentTimeMillis();
        System.out.println("----- cost time: " + (endTime - startTime) + " ms -----");

        System.out.println(Thread.currentThread().getName() + " end...");
    }

    @Test
    public void m2() throws ExecutionException, InterruptedException {
        //3个任务，目前开启多个异步线程来处理
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        long startTime = System.currentTimeMillis();

        FutureTask<String> futureTask1 = new FutureTask<String>(() -> {
            try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace();}
            return "task1 over...";
        });
        threadPool.submit(futureTask1);
        FutureTask<String> futureTask2 = new FutureTask<String>(() -> {
            try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace();}
            return "task2 over...";
        });
        threadPool.submit(futureTask2);
        //让main线程执行
        try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace();}

        //打印返回值
        //System.out.println(futureTask1.get());
        //System.out.println(futureTask2.get());

        long endTime = System.currentTimeMillis();

        //显示小于500ms时因为主线程的执行为sleep 300ms，主线程打印时子线程还在运行
        System.out.println("----- cost time: " + (endTime - startTime) + " ms -----");

        threadPool.shutdown();
        System.out.println(Thread.currentThread().getName() + " end...");
    }

    @Test
    public void m3() throws ExecutionException, InterruptedException {
        //3个任务，目前开启多个异步线程来处理
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        long startTime = System.currentTimeMillis();

        FutureTask<String> futureTask1 = new FutureTask<String>(() -> {
            try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace();}
            return "task1 over...";
        });
        threadPool.submit(futureTask1);
        FutureTask<String> futureTask2 = new FutureTask<String>(() -> {
            try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace();}
            return "task2 over...";
        });
        threadPool.submit(futureTask2);
        //让main线程执行
        try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace();}

        //打印返回值
        System.out.println(futureTask1.get());
        System.out.println(futureTask2.get());

        long endTime = System.currentTimeMillis();

        System.out.println("----- cost time: " + (endTime - startTime) + " ms -----");
        threadPool.shutdown();
        System.out.println(Thread.currentThread().getName() + " end...");
    }

}
