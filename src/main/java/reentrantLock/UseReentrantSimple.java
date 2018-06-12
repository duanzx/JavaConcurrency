package reentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UseReentrantSimple {

    private Lock lock = new ReentrantLock();

    public void method1() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 进入方法1");
            Thread.sleep(10000);
            System.out.println(Thread.currentThread().getName() + " 退出方法1");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void method2() {
        try {
//            lock.lock();
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + " 进入方法2");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " 退出方法2");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //测试当t1线程访问方法一并持有锁的时候，t2是否可以访问方法二
    //测试当t1线程迟迟不释放锁的时候，线程二中断后，reentrantLock释放可以响应，并且在此时终止方法二的访问
    public static void main(String[] args) throws Exception {
        final UseReentrantSimple useReentrantSimple = new UseReentrantSimple();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                useReentrantSimple.method1();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                useReentrantSimple.method2();
            }
        }, "t2");
        t1.start();
        Thread.sleep(1000);
        t2.start();
        Thread.sleep(1000);
        System.out.println("2秒后结束线程2，此时是否ReentrantLock是否有响应");//如果如果中断后，如果t2还能进入方法二说明，并没有放弃持有锁，未响应
        t2.interrupt();
    }

}
