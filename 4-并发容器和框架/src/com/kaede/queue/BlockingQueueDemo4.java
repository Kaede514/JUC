package com.kaede.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author kaede
 * @create 2022-09-09 19:36
 */

public class BlockingQueueDemo4 {

    public static void main(String[] args) throws InterruptedException {
        //创建阻塞队列
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        blockingQueue.offer("a",3L, TimeUnit.SECONDS);
        blockingQueue.offer("b",3L, TimeUnit.SECONDS);
        blockingQueue.offer("c",3L, TimeUnit.SECONDS);
        System.out.println(blockingQueue.element());
        System.out.println(blockingQueue.peek());
        blockingQueue.offer("w",3L, TimeUnit.SECONDS);

        System.out.println(blockingQueue.poll(3L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(3L, TimeUnit.SECONDS));
        //null
        System.out.println(blockingQueue.poll(3L, TimeUnit.SECONDS));
    }

}
