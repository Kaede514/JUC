package com.kaede.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author kaede
 * @create 2022-09-10 20:11
 */

public class CompletableFutureMallDemo {

    private static List<NetMall> list = Arrays.asList(
        new NetMall("JD"),
        new NetMall("TaoBao"),
        new NetMall("DangDang")
    );

    //step by step
    //List<NetMall> --> List<String>
    private static List<String> getPrice(List<NetMall> list, String productName) {
        //Stream流式计算
        return list
                .stream()
                .map(netMall ->
                    String.format("《%s》in %s price is %.2f", productName,
                            netMall.getNetMallName(),
                            netMall.calcPrice(productName)))
                .collect(Collectors.toList());
    }

    //all in
    //List<NetMall> -> List<CompletableFuture<String>> --> List<String>
    private static List<String> getPriceByCompletableFuture(List<NetMall> list, String productName) {
         return list
                .stream()
                .map(netMall ->
                    CompletableFuture.supplyAsync(() -> {
                    return String.format("《%s》in %s price is %.2f", productName,
                        netMall.getNetMallName(),
                        netMall.calcPrice(productName));}))
                .collect(Collectors.toList())
                .stream()
                .map(s -> s.join()).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        getPrice(list, "mysql").forEach(x -> System.out.println(x));

        long endTime = System.currentTimeMillis();
        System.out.println("----- cost time: " + (endTime - startTime) + " ms -----");

        startTime = System.currentTimeMillis();

        getPriceByCompletableFuture(list, "mysql").forEach(x -> System.out.println(x));

        endTime = System.currentTimeMillis();
        System.out.println("----- cost time: " + (endTime - startTime) + " ms -----");
    }

}

class NetMall {
    private String netMallName;

    public NetMall(String netMallName) {
        this.netMallName = netMallName;
    }

    public String getNetMallName() {
        return netMallName;
    }

    public double calcPrice(String productName) {
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace();}
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }
}
