package com.kaede.aqs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author kaede
 * @create 2022-09-15 17:11
 */

public class AQSDemo {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        lock.lock();
        try {
            
        } finally {
            lock.unlock();
        }
    }

}
