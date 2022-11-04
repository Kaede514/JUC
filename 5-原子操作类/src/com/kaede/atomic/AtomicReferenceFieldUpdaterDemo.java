package com.kaede.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author kaede
 * @create 2022-09-13 14:11
 *
 * 需求：多线程并发调用一个类的初始化方法，如果未被初始化过，则进行
 *      初始化，要求只能被初始化一次，只有一个线程操作成功
 */

public class AtomicReferenceFieldUpdaterDemo {

    public static void main(String[] args) throws InterruptedException {
        MyResource myResource = new MyResource();
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                try {
                    myResource.init(myResource);
                } finally {
                    countDownLatch.countDown();
                }
            },String.valueOf(i)).start();
        }

        countDownLatch.await();
        System.out.println("result = " + myResource.getInit());
    }

}

class MyResource {
    private volatile Boolean isInit = Boolean.FALSE;

    private AtomicReferenceFieldUpdater<MyResource,Boolean> referenceFieldUpdater =
        AtomicReferenceFieldUpdater.newUpdater(MyResource.class, Boolean.class, "isInit");

    public void init(MyResource myResource) {
        if (referenceFieldUpdater.compareAndSet(myResource,Boolean.FALSE,Boolean.TRUE)) {
            System.out.println(Thread.currentThread().getName() + " excute init...");
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace();}
            System.out.println(Thread.currentThread().getName() + " init over...");
        } else {
            System.out.println(Thread.currentThread().getName() + " wait the thread...");
        }
    }

    public Boolean getInit() {
        return isInit;
    }

    public void setInit(Boolean init) {
        isInit = init;
    }
}
