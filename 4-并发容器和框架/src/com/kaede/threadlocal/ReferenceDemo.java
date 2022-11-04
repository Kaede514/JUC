package com.kaede.threadlocal;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author kaede
 * @create 2022-09-14 15:21
 */

public class ReferenceDemo {

    public static void main(String[] args) {
        //strongReference();
        //softReference();
        //weakReference();
        phantomReference();
    }

    private static void phantomReference() {
        MyObject myObject = new MyObject();
        ReferenceQueue<MyObject> referenceQueue = new ReferenceQueue<>();
        PhantomReference<MyObject> phantomReference = new PhantomReference<>(myObject,referenceQueue);
        System.out.println("phantomReference.get() = " + phantomReference.get());
        System.out.println("--------------------------");

        List<byte[]> list = new ArrayList<>();
        new Thread(() -> {
            while(true) {
                //设置运行该线程的虚拟机内存大小后，-Xms10m -Xmx10m
                list.add(new byte[1 * 1024 * 1024]);
                try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace();}
                System.out.println("phantomReference.get() = " + phantomReference.get());
            }
        }).start();

        new Thread(() -> {
            while(true) {
                Reference<? extends MyObject> reference = referenceQueue.poll();
                if(reference != null) {
                    //说明有虚对象加入了队列
                    System.out.println("phantomReference was recycled and queued...");
                    break;
                }
            }
        }).start();
    }

    private static void weakReference() {
        WeakReference<MyObject> weakReference = new WeakReference<>(new MyObject());
        System.out.println("----- before GC weakReference: " + weakReference.get());
        System.gc();
        try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace();}
        System.out.println("----- after GC weakReference: " + weakReference.get());
    }

    private static void softReference() {
        SoftReference<MyObject> softReference = new SoftReference<>(new MyObject());
        System.out.println("----- before GC softReference: " + softReference.get());
        System.gc();
        try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace();}
        //当前内存充足，不会回收
        System.out.println("----- after GC enough memory softReference: " + softReference.get());
        //设置运行该线程的虚拟机内存大小后，-Xms10m -Xmx10m
        try {
            byte[] bytes = new byte[10 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("----- after GC not enough memory softReference: " + softReference.get());
        }
    }

    private static void strongReference() {
        MyObject myObject = new MyObject();
        System.out.println("----- before GC -----");
        System.out.println(myObject);
        myObject = null;
        //提醒系统GC，不保证一定会触发GC
        System.gc();
        //暂停一会，GC是后台线程，保证finalize()方法先于下面执行，方便观察
        try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace();}
        System.out.println("----- after GC -----");
        System.out.println(myObject);
    }

}

class MyObject {
    //此方法一般不用重写
    @Override
    protected void finalize() {
        //finalize的通常目的是在对象被不可撤销地丢弃之前执行清理操作
        System.out.println("----- invoke finalize method -----");
    }
}
