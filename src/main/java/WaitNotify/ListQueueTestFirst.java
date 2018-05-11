package WaitNotify;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duanzx on 2018/5/11.
 */
public class ListQueueTestFirst {
    //Wait
    private volatile static List<Integer> queueList = new ArrayList<Integer>();

    public synchronized void addQueue(int ele) {
        queueList.add(ele);
        System.out.println("--- " + queueList.size() + " ------");
    }

    public int getListSize(){
        return queueList.size();
    }

    //
    public static void main(String[] args) throws Exception {


        final ListQueueTestFirst listQueueTestFirst = new ListQueueTestFirst();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int x = 0; x < 10; x++) {
                        listQueueTestFirst.addQueue(x);
                        System.out.println("" + Thread.currentThread().getName() + "");
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
                        throw new RuntimeException("��ǰ�̣߳�" + Thread.currentThread().getName() +" ֹͣt1�̣߳�����������������");
                    }
                }
            }
        });
        t2.start();
    }
}
