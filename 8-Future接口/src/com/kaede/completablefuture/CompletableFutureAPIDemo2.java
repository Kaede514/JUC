package com.kaede.completablefuture;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author kaede
 * @create 2022-09-11 12:29
 */

public class CompletableFutureAPIDemo2 {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        try {
            m1(threadPool);
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace();}
            m2(threadPool);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    private static void m1(ExecutorService threadPool) {
        CompletableFuture.supplyAsync(() -> {
           try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace();}
            System.out.println("step 1...");
           return 1;
        }, threadPool).thenApply(f -> {
            int i = 1 / 0;
            System.out.println("step 2...");
            return f + 1;
        }).thenApply(f -> {
            System.out.println("step 3...");
            return f + 2;
        }).whenComplete((v,e) -> {
            System.out.println("----- result " + v + " -----");
        }).exceptionally(e -> {
            e.printStackTrace();
            return null;
        });
    }

    private static void m2(ExecutorService threadPool) {
        CompletableFuture.supplyAsync(() -> {
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace();}
            System.out.println("step 1...");
            return 1;
        }, threadPool).handle((f,e) -> {
            int i = 1 / 0;
            System.out.println("step 2...");
            return f + 1;
        }).handle((f,e) -> {
            System.out.println("step 3...");
            return f + 2;
        }).whenComplete((v,e) -> {
            System.out.println("----- result " + v + " -----");
        }).exceptionally(e -> {
            e.printStackTrace();
            return null;
        });
    }

}
