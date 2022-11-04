package com.kaede.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author kaede
 * @create 2022-09-16 10:07
 */

public class ReentrantReadWriteLockDemo {

    public static void main(String[] args) {
        MyResource myResource = new MyResource();

        for (int i = 1; i <= 3; i++) {
            int finalI = i;
            new Thread(() -> {
                myResource.write(finalI+"", finalI+"");
            },String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            int finalI = i;
            new Thread(() -> {
                myResource.read(finalI+"");
            },String.valueOf(i)).start();
        }

        for (int i = 1; i <= 1; i++) {
            int finalI = i;
            new Thread(() -> {
                myResource.write(finalI+"", finalI+"");
            },String.valueOf(i+10)).start();
        }
    }

}

class MyResource {

    Map<String,String> map = new HashMap<>();
    Lock lock = new ReentrantLock();
    ReadWriteLock rwLock =  new ReentrantReadWriteLock();

    public void write(String key, String value) {
        //lock.lock();
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 正在写入...");
            map.put(key, value);
            try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace();}
            System.out.println(Thread.currentThread().getName() + " 写入完成...");
        } finally {
            //lock.unlock();
            rwLock.writeLock().unlock();
        }
    }

    public void read(String key) {
        //lock.lock();
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 正在读取...");
            String result = map.get(key);
            try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace();}
            System.out.println(Thread.currentThread().getName() + " 读取完成..." + result);
        } finally {
            //lock.unlock();
            rwLock.readLock().unlock();
        }
    }

}
