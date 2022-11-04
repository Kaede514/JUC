package com.kaede.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author kaede
 * @create 2022-09-09 13:24
 *
 * 比较Runnable接口和Callable接口
 *
 */

public class Demo1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Callable接口创建线程(报错)
        //new Thread(new MyThread2(), "thread`");

        //Runnable接口有一个实现类FutureTask，在其中的构造器中可以传入Callable接口
        FutureTask<Integer> futureTask1 = new FutureTask<>(new MyThread1());
        //也可以使用lambda表达式
        FutureTask<Integer> futureTask2 = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + " come in callable");
            return 1024;
        });

        //创建一个线程
        new Thread(futureTask2, "threadA").start();
        new Thread(futureTask1, "threadB").start();

        //isDone()方法计算是否完成
        while(!futureTask2.isDone()) {
            System.out.println("wait...");
        }

        //调用FutureTask的get()方法，得到结果
        System.out.println(futureTask2.get());
        System.out.println(Thread.currentThread().getName() + " over...");
    }

}

class MyThread1 implements Callable {
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " come in callable");
        return 200;
    }
}
