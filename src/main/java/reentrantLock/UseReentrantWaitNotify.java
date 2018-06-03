package reentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UseReentrantWaitNotify {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void method1() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 进入方法1");
            condition.await();
            System.out.println(Thread.currentThread().getName() + " 开始执行方法1");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " 退出方法1");
            lock.unlock();
        }
    }

    public void method2() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 进入方法2");
            condition.signal();
            System.out.println(Thread.currentThread().getName() + " 方法2开始唤醒方法1");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " 退出方法2");
            lock.unlock();
        }
    }

    public static void main(String[] args) throws Exception {
        UseReentrantWaitNotify useReentrantWaitNotify = new UseReentrantWaitNotify();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                useReentrantWaitNotify.method1();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                useReentrantWaitNotify.method2();
            }
        }, "t2");
        t1.start();
        Thread.sleep(1000);
        t2.start();
    }
    //await方法是释放锁
}
