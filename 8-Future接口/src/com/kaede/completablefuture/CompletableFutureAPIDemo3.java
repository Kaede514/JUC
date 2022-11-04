package com.kaede.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author kaede
 * @create 2022-09-11 12:50
 */

public class CompletableFutureAPIDemo3 {

    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
           return 1;
        }).thenApply(f -> {
            return f + 1;
        }).thenApply(f -> {
            return f + 2;
        }).thenAccept(r -> {
            System.out.println("r = " + r);
        });

        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenRun(() -> {}).join());

        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenAccept((r) -> {
            System.out.println("r = " + r);
        }).join());

        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenApply((r) -> {
            return  r + " resultB";
        }).join());

    }

}
