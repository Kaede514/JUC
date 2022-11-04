package com.kaede.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author kaede
 * @create 2022-09-09 19:36
 */

public class BlockingQueueDemo {

    public static void main(String[] args) {
        //创建阻塞队列
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        //java.util.NoSuchElementException
        //System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        System.out.println(blockingQueue.element());
        System.out.println(blockingQueue.peek());
        //java.lang.IllegalStateException: Queue full
        //System.out.println(blockingQueue.add("w"));

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //java.util.NoSuchElementException
        //System.out.println(blockingQueue.remove());
    }

}
