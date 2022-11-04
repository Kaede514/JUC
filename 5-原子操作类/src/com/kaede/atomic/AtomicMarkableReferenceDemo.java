package com.kaede.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @author kaede
 * @create 2022-09-13 13:17
 */

public class AtomicMarkableReferenceDemo {

    private static AtomicMarkableReference<Integer> markableReference = new AtomicMarkableReference<>(100,false);

    public static void main(String[] args) {
        new Thread(() -> {
            boolean marked = markableReference.isMarked();
            System.out.println(Thread.currentThread().getName() + "  marked = " + marked);
            //等待t2线程取得相同的默认标识
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace();}
            markableReference.compareAndSet(100,1000,marked,!marked);
        },"t1").start();

        new Thread(() -> {
            boolean marked = markableReference.isMarked();
            System.out.println(Thread.currentThread().getName() + "  marked = " + marked);
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace();}
            boolean b = markableReference.compareAndSet(100, 2000, marked, !marked);
            System.out.println("t2 operate: " + b);
            System.out.println("markableReference.isMarked() = " + markableReference.isMarked());
            System.out.println("result = " + markableReference.getReference());
        },"t2").start();
    }

}
