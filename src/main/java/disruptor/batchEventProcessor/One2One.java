package disruptor.batchEventProcessor;

import com.lmax.disruptor.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by duanzx on 2018/6/12.
 * <p/>
 * 在Disruptor里，相邻的两个步骤被解释成步骤2中的EventProcessor依赖步骤1中的EventProcessor，消息在RingBuffer中依次被步骤1中的EventProcessor和步骤2中的EventProcessor处理。
 */
public class One2One {

    public static void main(String[] args) throws Exception {
       final RingBuffer<ValueEvent> ringBuffer = RingBuffer.createSingleProducer(new EventFactory<ValueEvent>() {
            @Override
            public ValueEvent newInstance() {
                return new ValueEvent();
            }
        }, 1024 * 1024, new YieldingWaitStrategy());
        SequenceBarrier barrier = ringBuffer.newBarrier();
        ValueEventHandler handler = new ValueEventHandler();
        BatchEventProcessor<ValueEvent> batchEventProcessor = new BatchEventProcessor<>(ringBuffer, barrier, handler);
        ringBuffer.addGatingSequences(batchEventProcessor.getSequence());
        ExecutorService executorService  = Executors.newFixedThreadPool(8);
        executorService.submit(batchEventProcessor);
        long seq;
        for (long i = 0; i < 1024 * 1024 * 8; i++) {
            seq = ringBuffer.next();//占个坑 --ringBuffer一个可用区块
            ringBuffer.get(seq).setValue(i);//给这个区块放入 数据
            ringBuffer.publish(seq);//发布这个区块的数据使handler(consumer)可见
        }
        Thread.sleep(1000);//等上1秒，等消费都处理完成
        batchEventProcessor.halt();//通知事件(或者说消息)处理器 可以结束了（并不是马上结束!!!）
        executorService.shutdown();//终止线程
    }
}
