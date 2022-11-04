package com.kaede.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author kaede
 * @create 2022-09-11 13:35
 */

public class CompletableFutureAPIDemo4 {

    public static void main(String[] args) {
        CompletableFuture<String> playerA = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("playerA come in...");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "playerA";
        });

        CompletableFuture<String> playerB = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("playerB come in...");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "playerB";
        });

        CompletableFuture<String> playerC = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("playerC come in...");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "playerC";
        });

        CompletableFuture<String> tempWinner = playerA.applyToEither(playerB, f -> {
            return f;
        });

        CompletableFuture<String> result = playerC.applyToEither(tempWinner, f -> {
            return f + " is the winner";
        });

        System.out.println(Thread.currentThread().getName() + " --- winner = " + result.join());
    }

}
