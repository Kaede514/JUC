package com.kaede.lock_8;

import java.util.concurrent.TimeUnit;

/**
 * @author kaede
 * @create 2022-09-11 16:08
 */

public class lock_extend4 {
    public static void main(String[] args) throws Exception {
        Phone12 phone = new Phone12();

        new Thread(() -> {
            try {
                phone.sendEmail();
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

class Phone12 {
    public void sendEmail() {
        System.out.println("come in...");
        synchronized(this) {
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace();}
            System.out.println("sendEmail...");
        }
    }
}