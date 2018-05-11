package WaitNotify;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * Created by duanzx on 2018/5/11.
 */
public class ListQueueCountDownLatch {
    private volatile ArrayList queueList = new ArrayList();

    public void addQueue(int ele){
        System.out.println("向队列里添加元素："+ele);
        queueList.add(ele);
    }
    public int getQueueSize(){
        return queueList.size();
    }

    public static void main(String[] args)throws Exception{
        final CountDownLatch countDownLatch = new CountDownLatch(5);
        final ListQueueCountDownLatch listQueueCountDownLatch = new ListQueueCountDownLatch();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try{
                    for(int x=0;x<10;x++){
                        listQueueCountDownLatch.addQueue(x);
                        Thread.sleep(1000);
                        System.out.println("当前线程 "+Thread.currentThread().getName()+" 向队列里添加了一个元素");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

}
