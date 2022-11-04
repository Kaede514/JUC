package com.kaede.jmm;

import java.util.concurrent.TimeUnit;

/**
 * @author kaede
 * @create 2022-09-12 18:45
 */

public class VolatileAtomicDemo {

    public static void main(String[] args) {
        MyNumber myNumber1 = new MyNumber();
        MyNumber2 myNumber2 = new MyNumber2();
        MyNumber3 myNumber3 = new MyNumber3();

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myNumber1.add();
                }
            },String.valueOf(i)).start();
        }
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myNumber2.add();
                }
            },String.valueOf(i)).start();
        }
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myNumber3.add();
                }
            },String.valueOf(i)).start();
        }
        
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace();}
        System.out.println("myNumber1.number = " + myNumber1.number);
        System.out.println("myNumber2.number = " + myNumber2.number);
        System.out.println("myNumber3.number = " + myNumber3.number);
    }

}

class MyNumber  {
    public int number;

    public synchronized void add() {
        this.number++;
    }
}

class MyNumber2  {
    public int number;

    public void add() {
        this.number++;
    }
}

class MyNumber3  {
    public volatile int number;

    public void add() {
        this.number++;
    }
}
