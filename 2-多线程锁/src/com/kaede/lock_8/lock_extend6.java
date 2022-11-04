package com.kaede.lock_8;

import java.util.concurrent.TimeUnit;

/**
 * @author kaede
 * @create 2022-09-11 16:08
 */

public class lock_extend6 {
    public static void main(String[] args) throws Exception {
        Phone14 phone = new Phone14();

        new Thread(() -> {
            try {
                Phone14.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"threadA").start();

        Thread.sleep(100);

        new Thread(() -> {
            phone.sendEmail();
        },"threadB").start();
    }
}

class Phone14 {
    public void sendEmail() {
        System.out.println("email come in...");
        synchronized(Phone14.class) {
            System.out.println("sendEmail...");
        }
    }

    public static synchronized void sendSMS() {
        System.out.println("sms come in...");
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace();}
        System.out.println("sendSMS...");
    }
}