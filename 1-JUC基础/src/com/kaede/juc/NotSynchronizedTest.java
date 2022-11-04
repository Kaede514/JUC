package com.kaede.juc;

/**
 * @author kaede
 * @create 2022-10-31
 */

public class NotSynchronizedTest {

    //多次执行效果一般都不同
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        //第二步、创建多个线程，在多个线程中调用资源类的操作方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    ticket.sale();
                }
            }
        }, "threadA").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    ticket.sale();
                }
            }
        }, "threadB").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    ticket.sale();
                }
            }
        }, "threadC").start();
    }

}

//第一步、创建一个资源类，在资源类中创建属性和操作方法
class Ticket {
    private int number;

    public Ticket() {
        this.number = 30;
    }

    //多线程环境下需加锁，否则可能会出现多线程操作中的问题
    public void sale() {
        if(this.number > 0) {
            System.out.println(Thread.currentThread().getName() + " 卖出该票");
            System.out.println("目前还剩下 " + --this.number +" 张票");
        }
    }
}