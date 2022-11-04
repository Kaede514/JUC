package com.kaede.futuretask;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author kaede
 * @create 2022-09-10 17:22
 */

public class FutureAPIDemo {

    @Test
    public void test1() throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            System.out.println(Thread.currentThread().getName() + " come in...");
            //暂停一会儿线程
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace();}
            return "task over";
        });

        new Thread(futureTask, "t1").start();

        //get()方法需等待子线程执行完才能获得返回值，置于该处时会阻塞后面main线程的执行
        //System.out.println(futureTask.get());
        System.out.println(Thread.currentThread().getName() + " do something...");

        //过时不候，抛出异常
        System.out.println(futureTask.get(1, TimeUnit.SECONDS));
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            System.out.println(Thread.currentThread().getName() + " come in...");
            //暂停一会儿线程
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace();}
            return "task over";
        });

        new Thread(futureTask, "t1").start();

        //get()方法需等待子线程执行完才能获得返回值，置于该处时会阻塞后面main线程的执行
        //System.out.println(futureTask.get());
        System.out.println(Thread.currentThread().getName() + " do something...");

        while(true) {
            if(futureTask.isDone()) {
                System.out.println(futureTask.get());
                break;
            } else {
                try { TimeUnit.MILLISECONDS.sleep(1000); } catch (InterruptedException e) { e.printStackTrace();}
                System.out.println("正在处理中...");
            }
        }
    }

}
