package com.kaede.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author kaede
 * @create 2022-09-11 13:06
 */

public class CompletableFutureWithThreadPoolDemo {

    public static void main(String[] args) {
        m1();
        System.out.println("======================");
        m2();
    }

    private static void m1() {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        try {
            CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
                try { TimeUnit.MILLISECONDS.sleep(100); } catch (InterruptedException e) { e.printStackTrace();}
                System.out.println("task 1  ->  " + Thread.currentThread().getName());
            },threadPool).thenRun(() -> {
                try { TimeUnit.MILLISECONDS.sleep(100); } catch (InterruptedException e) { e.printStackTrace();}
                System.out.println("task 2  ->  " + Thread.currentThread().getName());
            }).thenRun(() -> {
                try { TimeUnit.MILLISECONDS.sleep(100); } catch (InterruptedException e) { e.printStackTrace();}
                System.out.println("task 3  ->  " + Thread.currentThread().getName());
            }).thenRun(() -> {
                try { TimeUnit.MILLISECONDS.sleep(100); } catch (InterruptedException e) { e.printStackTrace();}
                System.out.println("task 4  ->  " + Thread.currentThread().getName());
            });

            System.out.println(completableFuture.get());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    private static void m2() {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        try {
            CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
                try { TimeUnit.MILLISECONDS.sleep(100); } catch (InterruptedException e) { e.printStackTrace();}
                System.out.println("task 1  ->  " + Thread.currentThread().getName());
            },threadPool).thenRunAsync(() -> {
                try { TimeUnit.MILLISECONDS.sleep(100); } catch (InterruptedException e) { e.printStackTrace();}
                System.out.println("task 2  ->  " + Thread.currentThread().getName());
            }).thenRunAsync(() -> {
                try { TimeUnit.MILLISECONDS.sleep(100); } catch (InterruptedException e) { e.printStackTrace();}
                System.out.println("task 3  ->  " + Thread.currentThread().getName());
            },threadPool).thenRun(() -> {
                try { TimeUnit.MILLISECONDS.sleep(100); } catch (InterruptedException e) { e.printStackTrace();}
                System.out.println("task 4  ->  " + Thread.currentThread().getName());
            });

            System.out.println(completableFuture.get());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

}
