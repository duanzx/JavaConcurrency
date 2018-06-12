package disruptor.one2threeMultiCast;

import com.lmax.disruptor.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClass {
    public static void main(String[] args)throws Exception{
       RingBuffer<ValueEvent> ringBuffer =  RingBuffer.createSingleProducer(new EventFactory<ValueEvent>() {
            @Override
            public ValueEvent newInstance() {
                return new ValueEvent();
            }
        },1024*1024,new YieldingWaitStrategy());
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();
        BatchEventProcessor<ValueEvent> firstEventProcessor = new BatchEventProcessor(ringBuffer,sequenceBarrier,new FirstValueEventHandler());
        BatchEventProcessor<ValueEvent> secondEventProcessor = new BatchEventProcessor(ringBuffer,sequenceBarrier,new SecondValueEventHandler());
        BatchEventProcessor<ValueEvent> thirdEventProcessor = new BatchEventProcessor(ringBuffer,sequenceBarrier,new ThirdValueEventHandler());
        ringBuffer.addGatingSequences(firstEventProcessor.getSequence(),secondEventProcessor.getSequence(),thirdEventProcessor.getSequence());
        for(long i =0 ;i<1024*1024;i++){
            long sequence = ringBuffer.next();
            ringBuffer.get(sequence).setValue(i);
            ringBuffer.publish(sequence);
        }
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.execute(firstEventProcessor);
        executorService.execute(secondEventProcessor);
        executorService.execute(thirdEventProcessor);
        executorService.shutdown();
    }
}
