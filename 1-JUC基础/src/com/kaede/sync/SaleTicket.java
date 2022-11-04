package com.kaede.sync;

/**
 * @author kaede
 * @create 2022-10-31
 */

public class SaleTicket {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
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

class Ticket {
    private int number;

    public Ticket() {
        this.number = 30;
    }

    public synchronized void sale() {
        if(this.number > 0) {
            System.out.println(Thread.currentThread().getName() + " 卖出该票");
            System.out.println("目前还剩下 " + --this.number +" 张票");
        }
    }
}
