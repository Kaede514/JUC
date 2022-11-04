package com.kaede.juc;

/**
 * @author kaede
 * @create 2022-10-31
 */

public class DaemonTest {

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            //isDaemon()判断是否为守护线程，true为守护线程，false为用户线程
            System.out.println(Thread.currentThread().getName() + ": " + Thread.currentThread().isDaemon());
            while (true) {
                //若主线程已结束，但用户线程还在运行，则JVM仍为存活状态
            }
        }, "threadA");
        //设为守护线程，若主线程已结束，无用户线程，只剩下守护线程，则JVM会结束
        //setDaemon()方法必须在start()之前设置，否则报IllegalThreadException异常
        threadA.setDaemon(true);
        //运行线程
        threadA.start();
        //查看当前线程
        System.out.println(Thread.currentThread().getName() + " over...");
    }

}
