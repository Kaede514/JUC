package com.kaede.lock_8;

/**
 * @author kaede
 * @create 2022-09-08 20:15
 *
 * 6、两个静态同步方法，2部手机，先打印SMS还是Email
 *    sendSMS...
 *    sendEmail...
 */

public class Lock_8_6 {
    public static void main(String[] args) throws Exception {
        Phone6 phone1 = new Phone6();
        Phone6 phone2 = new Phone6();

        new Thread(() -> {
            try {
                phone1.sendSMS();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"threadA").start();

        Thread.sleep(100);

        new Thread(() -> {
            phone2.sendEmail();
        },"threadB").start();
    }
}

class Phone6 {
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
