package WaitNotify;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duanzx on 2018/5/11.
 */
public class ListQueueTestFirst {
    //Wait方法释放锁，Notify方法不释放锁
    private volatile static List<Integer> queueList = new ArrayList<Integer>();

    public synchronized void addQueue(int ele) {
        queueList.add(ele);
        System.out.println("---已经添加了 " + queueList.size() + " 元素------");
    }

    public int getListSize(){
        return queueList.size();
    }

    //如何使用线程池创建线程！！！！！！！！
    public static void main(String[] args) throws Exception {
        //t1线程负责向 queueList里添加元素
        //t2.负责监听t1，当添加到元素的个数等于5时，t2需要通知t1,停止添加元素
        //最终模式：使用list模拟一个队列！！！！！！！！！！

        final ListQueueTestFirst listQueueTestFirst = new ListQueueTestFirst();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int x = 0; x < 10; x++) {
                        listQueueTestFirst.addQueue(x);
                        System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素");
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(listQueueTestFirst.getListSize()==5){
                        throw new RuntimeException("当前线程：" + Thread.currentThread().getName() +" 停止t1线程！！！！！！！！！");
                    }
                }
            }
        });
        t2.start();
    }
}
