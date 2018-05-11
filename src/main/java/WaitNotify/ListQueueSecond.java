package WaitNotify;

import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.ArrayList;
import java.util.List;

public class ListQueueSecond {
    private final List<Object> list = new ArrayList<>();
    private final int MIN_SIZE = 0;
    private final int MAX_SIZE;
    private volatile int count = 1; // 0 1 2 3 4 5
    private final Object lock = new Object();

    public ListQueueSecond(int maxSize) {
        this.MAX_SIZE = maxSize;
    }

    public void put(Object object) {
        synchronized (lock) {
            if (count < MAX_SIZE) {
                count++;
                lock.notify();//可以拿走了
                list.add(object);
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
            if (count > MIN_SIZE) {
                count--;
                lock.notify();
                return list.remove(0);
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
        final ListQueueSecond listQueueTest = new ListQueueSecond(5);
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
}
