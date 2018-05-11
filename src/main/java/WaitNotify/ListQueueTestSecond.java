package WaitNotify;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duanzx on 2018/5/11.
 */

//调用某个对象的wait()方法，相当于让当前线程交出此对象的monitor，然后进入等待状态，等待后续再次获得此对象的锁（Thread类中的sleep方法使当前线程暂停执行一段时间，从而让其他线程有机会继续执行，但它并不释放对象锁）
//notify()方法能够唤醒一个正在等待该对象的monitor的线程，当有多个线程都在等待该对象的monitor的话，则只能唤醒其中一个线程，具体唤醒哪个线程则不得而知。

public class ListQueueTestSecond {
    private volatile static List<Integer> queueList = new ArrayList<Integer>();
    public void addQueue(int ele) {
        queueList.add(ele);
        System.out.println("---已经添加了 " + queueList.size() + " 元素------");
    }

    public int getListSize(){
        return queueList.size();
    }

    public static void main(String[] args)throws Exception{
        final Object lock = new Object();
        final ListQueueTestSecond listQueueTestSecond = new ListQueueTestSecond();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    try {
                        for (int x = 0; x < 10; x++) {
                            listQueueTestSecond.addQueue(x);
                            System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素");
                            Thread.sleep(1000);
                            if(listQueueTestSecond.getListSize() == 5){
                                System.out.println("当前线程添加了5个元素，达到限制，此时发出通知");
                                lock.notify();//此时不释放锁，将继续执行循环
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    try{
                        if(listQueueTestSecond.getListSize() != 5){
                            lock.wait();
                        }
                        System.out.println("当前线程"+Thread.currentThread().getName()+" 收到通知停止");
                        throw new RuntimeException();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        t2.start();
        t1.start();
    }
}
