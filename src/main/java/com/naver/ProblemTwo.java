package com.naver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author tanchaowen
 */
public class ProblemTwo<K,V> {

    private Map<K,V> cache = new HashMap<>();

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private Lock readLock = readWriteLock.readLock();
    private Lock writeLock = readWriteLock.writeLock();

    /**
     * 获取缓存值
     */
    public V get(K key) {
        //获取读锁
        readLock.lock();
        try {
            V value = cache.get(key);
            if (value == null) {
                //关闭读锁，获取写锁
                readLock.unlock();
                writeLock.lock();
                //缓存是空，重新加载数据到到缓存
                load(key);
                //完成写操作后，应该在写锁释放之前获取到读锁
                writeLock.unlock();
                readLock.lock();
            }
            value = cache.get(key);
            return value;
        }finally {
            readLock.unlock();
        }
    }

    /**
     * 加载指定key数据到缓存中
     *
     * @param key
     */
    public void load(K key){
        writeLock.lock();
        try {
            cache.put(key, (V) "100");
        }finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {

        ProblemTwo<String,Object> lock = new ProblemTwo();
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName()+": "+lock.get("tan"));
        };

        Thread[] threadArray = new Thread[10];
        for (int i = 0; i < 5; i++) {
            threadArray[i] = new Thread(runnable);
        }

        for (int i = 0; i < 5; i++) {
            threadArray[i].start();
        }
    }

}
