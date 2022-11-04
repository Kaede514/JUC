package com.kaede.lock_8;

import java.util.concurrent.TimeUnit;

/**
 * @author kaede
 * @create 2022-09-11 16:08
 */

public class lock_extend5 {
    public static void main(String[] args) throws Exception {
        Phone13 phone = new Phone13();

        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"threadA").start();

        Thread.sleep(100);

        new Thread(() -> {
            phone.sendSMS();
        },"threadB").start();
    }
}

class Phone13 {
    public void sendEmail() {
        System.out.println("email come in...");
        synchronized(this) {
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace();}
            System.out.println("sendEmail...");
        }
    }

    public void sendSMS() {
        System.out.println("sms come in...");
        synchronized(this) {
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace();}
            System.out.println("sendSMS...");
        }
    }
}