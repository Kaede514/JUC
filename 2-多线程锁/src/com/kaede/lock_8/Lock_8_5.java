package com.kaede.lock_8;

/**
 * @author kaede
 * @create 2022-09-08 20:15
 *
 * 5、两个静态同步方法，1部手机，先打印SMS还是Email
 *    sendSMS...
 *    sendEmail...
 */

public class Lock_8_5 {
    public static void main(String[] args) throws Exception {
        Phone5 phone = new Phone5();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"threadA").start();

        Thread.sleep(100);

        new Thread(() -> {
            phone.sendEmail();
        },"threadB").start();
    }
}

class Phone5 {
    public static synchronized void sendSMS() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("sendSMS...");
    }

    public static synchronized void sendEmail() {
        System.out.println("sendEmail...");
    }

    public void getHello() {
        System.out.println("getHello...");
    }
}
