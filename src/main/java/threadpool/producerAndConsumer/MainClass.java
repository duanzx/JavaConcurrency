package threadpool.producerAndConsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by duanzx on 2018/5/18.
 */
public class MainClass {

    public static void main(String[] args)throws Exception{
        BlockingQueue<String> blockingQueue= new LinkedBlockingDeque<>(10);
        Producer p1 = new Producer(blockingQueue);
        Producer p2 = new Producer(blockingQueue);
        Producer p3 = new Producer(blockingQueue);
        Consumer c1 = new Consumer(blockingQueue);
        Consumer c2 = new Consumer(blockingQueue);
        Consumer c3 = new Consumer(blockingQueue);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(p1);
        executorService.execute(p2);
        executorService.execute(p3);
        executorService.execute(c1);
        executorService.execute(c2);
        executorService.execute(c3);
        Thread.sleep(5000);

    }
}
