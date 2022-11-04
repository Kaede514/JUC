package com.kaede.completablefuture;

import java.util.concurrent.*;

/**
 * @author kaede
 * @create 2022-09-10 19:23
 */

public class CompletableFutureUseDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //m1();
        //m2();
        //m3();
        //m4();
    }

    private static void m1() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " come in...");
            int result = ThreadLocalRandom.current().nextInt(10);
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace();}
            System.out.println("result = " + result);
            return result;
        });

        System.out.println(Thread.currentThread().getName() + " do something...");

        System.out.println(completableFuture.get());
    }

    private static void m2() {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " come in...");
            int result = ThreadLocalRandom.current().nextInt(10);
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace();}
            System.out.println("result = " + result);
            return result;
        }).whenComplete((v,e) -> {
            //v是上一步的返回值，e是产生的异常
            System.out.println("v = " + v);
            System.out.println("e = " + e);
        }).exceptionally(e -> {
            //异常处理
            e.printStackTrace();
            System.out.println("exception msg: " + e.getMessage());
            return null;
        });

        System.out.println(Thread.currentThread().getName() + " do something...");
    }

    private static void m3() {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " come in...");
            int result = ThreadLocalRandom.current().nextInt(10);
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace();}
            System.out.println("result = " + result);
            return result;
        }).whenComplete((v,e) -> {
            //v是上一步的返回值，e是产生的异常
            System.out.println("v = " + v);
            System.out.println("e = " + e);
        }).exceptionally(e -> {
            //异常处理
            e.printStackTrace();
            System.out.println("exception msg: " + e.getMessage());
            return null;
        });

        System.out.println(Thread.currentThread().getName() + " do something...");

        //方法1：主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace();}
    }

    private static void m4() {
        //方法2：自定义线程池，然后手动关闭
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        try {
            CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + " come in...");
                int result = ThreadLocalRandom.current().nextInt(10);
                try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace();}
                System.out.println("result = " + result);

                int i = 1 / 0;

                return result;
            }, threadPool).whenComplete((v,e) -> {
                //v是上一步的返回值，e是产生的异常
                System.out.println("v = " + v);
                System.out.println("e = " + e);
            }).exceptionally(e -> {
                //异常处理
                e.printStackTrace();
                System.out.println("exception msg: " + e.getMessage());
                return null;
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

        System.out.println(Thread.currentThread().getName() + " do something...");
    }

}
