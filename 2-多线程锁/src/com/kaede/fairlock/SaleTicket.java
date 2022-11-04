package com.kaede.fairlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author kaede
 * @create 2022-09-08 16:00
 */

public class SaleTicket {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                ticket.sale();
            }
        }, "threadA").start();
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                ticket.sale();
            }
        }, "threadB").start();
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                ticket.sale();
            }
        }, "threadC").start();
    }

}

class Ticket {
    private int number;

    public Ticket() {
        this.number = 30;
    }

    //创建可重入锁，true为公平锁，false为非公平锁，默认为false
    private final ReentrantLock lock = new ReentrantLock(true);

    public void sale() {
        lock.lock();
        try {
            if(this.number > 0) {
                System.out.println(Thread.currentThread().getName() + " 卖出该票");
                System.out.println("目前还剩下 " + --this.number +" 张票");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
