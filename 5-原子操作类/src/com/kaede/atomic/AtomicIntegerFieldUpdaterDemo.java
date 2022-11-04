package com.kaede.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author kaede
 * @create 2022-09-13 13:53
 */

public class AtomicIntegerFieldUpdaterDemo {

    public static void main(String[] args) throws InterruptedException {
        BankAccount bankAccount = new BankAccount();
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 1000; j++) {
                        //bankAccount.add();
                        bankAccount.transMoney(bankAccount);
                    }
                } finally {
                    countDownLatch.countDown();
                }
            },String.valueOf(i)).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "   result = " + bankAccount.getMoney());
    }

}

class BankAccount {
    private String bankName = "CCB";
    private volatile int money = 0;

    private AtomicIntegerFieldUpdater<BankAccount> fieldUpdater =
        AtomicIntegerFieldUpdater.newUpdater(BankAccount.class,"money");

    //不加synchronized，保证高性能原子性
    public void transMoney(BankAccount bankAccount) {
        fieldUpdater.getAndIncrement(bankAccount);
    }

    public synchronized void add() {
        this.money++;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
