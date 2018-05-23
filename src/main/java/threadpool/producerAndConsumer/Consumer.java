package threadpool.producerAndConsumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by duanzx on 2018/5/18.
 */
public class Consumer implements Runnable{
    private BlockingQueue<String> blockingQueue;
    private static Random random = new Random();

    public Consumer(BlockingQueue blockingQueue){
        this.blockingQueue=blockingQueue;
    }


    @Override
    public void run() {
        try {
            String element = blockingQueue.take();
            Thread.sleep(random.nextInt(1000));
            System.out.println(Thread.currentThread().getName()+"获取到元素"+element + "，消费成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
