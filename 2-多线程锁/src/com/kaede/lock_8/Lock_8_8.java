package com.kaede.lock_8;

/**
 * @author kaede
 * @create 2022-09-08 20:15
 *
 * 8、1个静态同步方法，1个普通方法，2部手机，先打印SMS还是Email
 *    sendEmail...
 *    sendSMS...
 */

public class Lock_8_8 {
    public static void main(String[] args) throws Exception {
        Phone8 phone1 = new Phone8();
        Phone8 phone2 = new Phone8();

        new Thread(() -> {
            try {
                phone1.sendSMS();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "threadA").start();

        Thread.sleep(100);

        new Thread(() -> {
            phone2.sendEmail();
        }, "threadB").start();
    }
}

class Phone8 {
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
