package com.kaede.lock_8;

/**
 * @author kaede
 * @create 2022-09-11 16:08
 */

public class lock_extend3 {
    public static void main(String[] args) throws Exception {
        Phone11 phone = new Phone11();

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

class Phone11 {
    public void sendSMS() throws InterruptedException {
        synchronized(this) {
            Thread.sleep(3000);
            System.out.println("sendSMS...");
        }
    }

    public void sendEmail() {
        synchronized(Phone10.class) {
            System.out.println("sendEmail...");
        }
    }
}