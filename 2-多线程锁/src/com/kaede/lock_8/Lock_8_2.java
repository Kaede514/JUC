package com.kaede.lock_8;

/**
 * @author kaede
 * @create 2022-09-08 20:15
 *
 * 2、在SMS方法中停3s，先打印Email还是SMS
 *    sendSMS...
 *    sendEmail...
 */

public class Lock_8_2 {
    public static void main(String[] args) throws Exception {
        Phone2 phone = new Phone2();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "threadA").start();

        Thread.sleep(100);

        new Thread(() -> {
            phone.sendEmail();
        }, "threadB").start();
    }
}

class Phone2 {
    public synchronized void sendSMS() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("sendSMS...");
    }

    public synchronized void sendEmail() {
        System.out.println("sendEmail...");
    }

    public void getHello() {
        System.out.println("getHello...");
    }
}
