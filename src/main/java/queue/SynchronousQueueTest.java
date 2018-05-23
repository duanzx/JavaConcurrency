package queue;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueTest {
    //线程配对通信
    public static void main(String[] args) throws Exception {
        final SynchronousQueue<String> stringSynchronousQueue = new SynchronousQueue<String>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    stringSynchronousQueue.put("a");
                    System.out.println("start put a");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end put a");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(stringSynchronousQueue.take());
                    System.out.println("start take a");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end take a");
            }
        }).start();
    }
}
