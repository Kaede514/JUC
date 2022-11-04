package com.kaede.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author kaede
 * @create 2022-09-13 11:22
 */

public class ABADemo {

    private static AtomicInteger atomicInteger = new AtomicInteger(100);
    private static AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {
        abaHappen();
        abaSolve();
    }

    private static void abaHappen() {
        new Thread(() -> {
            atomicInteger.compareAndSet(100,101);
            try { TimeUnit.MILLISECONDS.sleep(100); } catch (InterruptedException e) { e.printStackTrace();}
            atomicInteger.compareAndSet(101, 100);
        },"t1").start();

        new Thread(() -> {
            try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace();}
            System.out.println(atomicInteger.compareAndSet(100, 2022) + "\t" + atomicInteger.get());
        },"t2").start();
    }

    private static void abaSolve() {
        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "  first version = " + stamp);
            //保证后面的t4线程初始得到的版本号和当前线程一致
            try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace();}

            stampedReference.compareAndSet(100,101,
                stampedReference.getStamp(),stampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + "  second version = " + stampedReference.getStamp());

            stampedReference.compareAndSet(101,100,
                stampedReference.getStamp(),stampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + "  third version = " + stampedReference.getStamp());
        },"t3").start();

        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "  first version = " + stamp);
            //等待t3线程发生ABA
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace();}

            boolean b = stampedReference.compareAndSet(100, 2022,
                stamp, stampedReference.getStamp() + 1);
            System.out.println("update " + b);
            System.out.println(Thread.currentThread().getName() + "  value = " + stampedReference.getReference()
                + "  version = " + stampedReference.getStamp());
        },"t4").start();
    }

}
