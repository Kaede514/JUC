package com.kaede.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author kaede
 * @create 2022-09-09 19:36
 */

public class BlockingQueueDemo2 {

    public static void main(String[] args) {
        //创建阻塞队列
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.element());
        System.out.println(blockingQueue.peek());
        //false
        System.out.println(blockingQueue.offer("w"));

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        //null
        System.out.println(blockingQueue.poll());
    }

}
