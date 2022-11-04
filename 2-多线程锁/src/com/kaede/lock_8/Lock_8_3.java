package com.kaede.lock_8;

/**
 * @author kaede
 * @create 2022-09-08 20:15
 *
 * 3、普通方法getHello和Email打印顺序
 *    getHello...
 *    sendSMS...
 */

public class Lock_8_3 {
    public static void main(String[] args) throws Exception {
        Phone3 phone = new Phone3();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"threadA").start();

        Thread.sleep(100);

        new Thread(() -> {
            phone.getHello();
        },"threadB").start();
    }
}

class Phone3 {
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
