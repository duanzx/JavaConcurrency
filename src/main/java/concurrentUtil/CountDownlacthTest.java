package concurrentUtil;

import java.util.concurrent.CountDownLatch;

/**
 * Created by duanzx on 2018/5/25
 * 经常用于监听某些初始化操作，等初始化操作完成后，通知主线程继续工作。.
 */
public class CountDownlacthTest {

    public static void main(String[] args)throws Exception{
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("start thread t1 waiting countdown");
                try {
                    countDownLatch.await();
                    System.out.println("start thread t1 end countdown");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for(int x=0;x<2;x++){
                        Thread.sleep(1000);
                        System.out.println("start thread t2 countDown");
                        countDownLatch.countDown();
                    };
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
