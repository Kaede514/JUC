package com.kaede.lock_8;

/**
 * @author kaede
 * @create 2022-09-11 16:08
 *
 * 测试方法内部使用synchronized(this)是否锁对象
 *
 */

public class lock_extend1 {
    public static void main(String[] args) throws Exception {
        Phone9 phone = new Phone9();

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

class Phone9 {
    public void sendSMS() throws InterruptedException {
        synchronized(this) {
            Thread.sleep(3000);
            System.out.println("sendSMS...");
        }
    }

    public void sendEmail() {
        synchronized(this) {
            System.out.println("sendEmail...");
        }
    }
}