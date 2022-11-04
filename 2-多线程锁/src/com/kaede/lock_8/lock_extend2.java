package com.kaede.lock_8;

/**
 * @author kaede
 * @create 2022-09-11 16:08
 *
 * 测试方法内部使用synchronized(Xxx.class)是否类
 *
 */

public class lock_extend2 {
    public static void main(String[] args) throws Exception {
        Phone10 phone = new Phone10();

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

class Phone10 {
    public void sendSMS() throws InterruptedException {
        synchronized(Phone10.class) {
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