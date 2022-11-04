package com.kaede.readwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author kaede
 * @create 2022-09-16 11:55
 */

public class LockDownGradingDemo {

    public static void main(String[] args) {
        ReentrantReadWriteLock rwLcok = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.WriteLock writeLock = rwLcok.writeLock();
        ReentrantReadWriteLock.ReadLock readLock = rwLcok.readLock();

        writeLock.lock();
        System.out.println("----- write... -----");
        readLock.lock();
        System.out.println("----- read... -----");
        writeLock.unlock();

        readLock.unlock();
    }

}
