package com.kaede.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author kaede
 * @create 2022-09-14 14:07
 */

public class ThreadLocalDemo2 {

    public static void main(String[] args) {
        MyData myData = new MyData();
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        try {
            for (int i = 1; i < 10; i++) {
                threadPool.submit(() -> {
                    try {
                        Integer beforeInt = myData.getThreadLocal().get();
                        myData.add();
                        Integer afterInt = myData.getThreadLocal().get();
                        System.out.println(Thread.currentThread().getName() +
                            "   beforeInt = " + beforeInt + "   afterInt = " + afterInt);
                    } finally {
                        //清理，否则会出现后续线程的变量值初始不为0的情况
                        myData.getThreadLocal().remove();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

}

class MyData {
    private ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    public void add() {
        threadLocal.set(threadLocal.get() + 1);
    }

    public ThreadLocal<Integer> getThreadLocal() {
        return threadLocal;
    }

    public void setThreadLocal(ThreadLocal<Integer> threadLocal) {
        this.threadLocal = threadLocal;
    }
}
