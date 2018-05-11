package WaitNotify;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by duanzx on 2018/5/11.
 */
public class ListQueueTestFirstOne {
    private final LinkedList<Object> list = new LinkedList<>();
    private final int MIN_SIZE = 0;
    private final int MAX_SIZE;
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private final Object lock = new Object();

    public ListQueueTestFirstOne(int maxSize) {
        this.MAX_SIZE = maxSize;
    }

    public void put(Object obj) {
        synchronized (lock) {
            if (atomicInteger.incrementAndGet() < MAX_SIZE) {
                list.add(obj);
                lock.notify();
                return;
            }
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Object take() {
        synchronized (lock) {
            if (atomicInteger.decrementAndGet() > MIN_SIZE) {
                lock.notify();
                return list.removeFirst();
            }
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        final ListQueueTestFirstOne listQueueTest = new ListQueueTestFirstOne(5);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int x = 0; x < 10; x++) {
                    listQueueTest.put("00" + x);
                    System.out.println("向队列里添加元素: 00" + x);
                }
            }
        });
        t1.start();
        Thread.sleep(1000);
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int x = 0; x < 5; x++) {
                    Object obj = listQueueTest.take();
                    System.out.println("向队列里取出元素：" + obj);
                }
            }
        });
        t2.start();
    }

    //取数据
    //加数据
    //队列
}
