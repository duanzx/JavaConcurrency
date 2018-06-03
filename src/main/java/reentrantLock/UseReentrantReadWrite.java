package reentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UseReentrantReadWrite {

    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();

    public void read() {
        try {
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + " 进入read");
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " 退出read");
            readLock.unlock();
        }
    }

    public void write() {
        try {
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + " 进入write");
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " 退出write");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) throws Exception {
        UseReentrantReadWrite useReentrantReadWrite = new UseReentrantReadWrite();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                useReentrantReadWrite.read();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                useReentrantReadWrite.read();
            }
        }, "t2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                useReentrantReadWrite.write();
            }
        }, "t3");
        t1.start();
        t2.start();
        t3.start();
    }
}
