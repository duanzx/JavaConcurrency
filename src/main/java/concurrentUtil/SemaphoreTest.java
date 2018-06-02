package concurrentUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

//80%的访问量在20%的时间内达到
//信号量，Semaphore是用来控制同时访问特定资源的线程数量，他通过协调各个线程，以保证合理的使用公共资源。
public class SemaphoreTest {

    public static void main(String[] args) throws Exception {
        Semaphore semaphore = new Semaphore(5);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int x = 0; x < 20; x++) {
            final int no = x;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println("semaphore acquire : " + no);
                        Thread.sleep(2000);
                        System.out.println("thread run :" + no);
                        System.out.println("semaphore release :" + no);
                        semaphore.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
    }
}
