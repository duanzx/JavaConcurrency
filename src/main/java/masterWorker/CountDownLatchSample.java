package masterWorker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by duanzx on 2018/5/18.
 */
public class CountDownLatchSample {

    public static void main(String[] args)throws Exception{
        final CountDownLatch countDownLatch = new CountDownLatch(10);
        for(int x=0;x<10;x++){
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("正在异步执行，耗时一秒钟");
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
        System.out.println("异步执行结束-----------");
    }
}
