package threadpool.producerAndConsumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by duanzx on 2018/5/18.
 */
public class Producer implements Runnable{
    private BlockingQueue<String> blockingQueue;
    private static AtomicInteger count = new AtomicInteger();
    private static Random random = new Random();

    @Override
    public void run() {
        try {
            Thread.sleep(random.nextInt(1000));
            String element = "元素["+count.incrementAndGet()+"]";
            System.out.println(Thread.currentThread().getName() + "开始将元素" + element + "加入到缓冲区中");
           boolean success =  blockingQueue.offer(element,2, TimeUnit.SECONDS);
            if(!success){
                System.out.println("------------提交到缓冲区失败---------");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public Producer(BlockingQueue blockingQueue){
        this.blockingQueue = blockingQueue;
    }

}
