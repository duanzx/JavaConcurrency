package disruptor.basic;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//      @param eventFactory   the factory to create events in the ring buffer.
//    * @param ringBufferSize the size of the ring buffer, must be power of 2.
//    * @param threadFactory  a {@link ThreadFactory} to create threads for processors.
//    * @param producerType   the claim strategy to use for the ring buffer.
//    * @param waitStrategy   the wait strategy to use for the ring buffer.

public class DisruptorMain {

    public static void main(String[] args) throws Exception {
        EventFactory<LongEvent> eventFactory = new LongEventFactory();
        ExecutorService executorService = Executors.newCachedThreadPool();
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(eventFactory, 1024 * 1024, executorService, ProducerType.SINGLE, new YieldingWaitStrategy());
        //注册Consumer
        disruptor.handleEventsWith(new LongEventHandler());
        disruptor.start();
        //生产EVENT
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        LongEventProducer producer = new LongEventProducer(ringBuffer);
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (long l = 0; l < 1024 * 1024; l++) {
            byteBuffer.putLong(0, l);
            producer.onData(byteBuffer);
        }
        disruptor.shutdown();
        executorService.shutdown();
    }


}
