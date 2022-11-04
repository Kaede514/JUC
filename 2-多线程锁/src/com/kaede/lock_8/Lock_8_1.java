package com.kaede.lock_8;

/**
 * @author kaede
 * @create 2022-09-08 20:15
 *
 * 8锁
 *
 * 1、标准访问时，先打印SMS还是Email
 *    sendSMS...
 *    sendEmail...
 */

public class Lock_8_1 {
    public static void main(String[] args) throws Exception {
        Phone phone = new Phone();

        new Thread(() -> {
            phone.sendSMS();
        },"threadA").start();

        Thread.sleep(100);

        new Thread(() -> {
            phone.sendEmail();
        },"threadB").start();
    }
}

class Phone {
    public synchronized void sendSMS() {
        System.out.println("sendSMS...");
    }

    public synchronized void sendEmail() {
        System.out.println("sendEmail...");
    }

    public void getHello() {
        System.out.println("getHello...");
    }
}
