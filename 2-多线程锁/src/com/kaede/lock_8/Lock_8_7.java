package com.kaede.lock_8;

/**
 * @author kaede
 * @create 2022-09-08 20:15
 *
 * 7、1个静态同步方法，1个普通方法，1部手机，先打印SMS还是Email
 *    sendEmail...
 *    sendSMS...
 */

public class Lock_8_7 {
    public static void main(String[] args) throws Exception {
        Phone7 phone = new Phone7();

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

class Phone7 {
    public static synchronized void sendSMS() throws InterruptedException {
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
