package disruptor.eventProcessor;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

public class MainClass {

    public static void main(String[] args) throws Exception {
        RingBuffer<Trade> ringBuffer = RingBuffer.createSingleProducer(new EventFactory<Trade>() {
            @Override
            public Trade newInstance() {
                return new Trade();
            }
        }, 1024 * 1024);
        ringBuffer.newBarrier();
//        BatchEventProcessor<Trade> batchEventProcessor = new BatchEventProcessor<>(ringBuffer);

    }
}
