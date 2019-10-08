package com.wallet.crypto.trustapp.entity;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheItem<T> {
    private T item;
    private final long liveTime;
    private final ReadWriteLock locker = new ReentrantReadWriteLock();
    private long time;

    public CacheItem(long j) {
        this.liveTime = j;
    }

    public T get() {
        return get(false);
    }

    public void reset() {
        try {
            this.locker.writeLock().lock();
            this.item = null;
            this.time = Long.MAX_VALUE;
        } finally {
            this.locker.writeLock().unlock();
        }
    }

    public void set(T t) {
        set(t, System.currentTimeMillis());
    }

    public T get(boolean z) {
        try {
            this.locker.readLock().lock();
            if (!z) {
                if (this.time <= System.currentTimeMillis() - this.liveTime) {
                    this.locker.readLock().unlock();
                    return null;
                }
            }
            T t = this.item;
            return t;
        } finally {
            this.locker.readLock().unlock();
        }
    }

    public void set(T t, long j) {
        try {
            this.locker.writeLock().lock();
            this.item = t;
            this.time = j;
        } finally {
            this.locker.writeLock().unlock();
        }
    }
}
