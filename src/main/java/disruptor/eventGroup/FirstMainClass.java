package disruptor.eventGroup;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FirstMainClass {

    /**
     * P1生产数据后，交由C1,C2并行执行完成后，再由C3执行
     * 使用BatchEventProcessor
     * 使用EventHandlerGroup
     * */
    public static void main(String[] args)throws Exception{
//        useByBatchEventProcessor();
        useByDisruptor();
    }

    public static void useByDisruptor(){
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Disruptor disruptor = new Disruptor(new EventFactory() {
            @Override
            public Object newInstance() {
                return new ValueEvent();
            }
        },1024*1024,executorService);
        EventHandler h1 = new FirstValueEventHandler();
        EventHandler h2 = new SecondValueEventHandler();
        EventHandler h3 = new ThirdValueEventHandler();
       EventHandlerGroup<ValueEvent> eventEventHandlerGroup =  disruptor.handleEventsWith(h1,h2);
//       disruptor.after(h1,h2).handleEventsWith(h3);
        eventEventHandlerGroup.then(h3);
        EventTranslatorOneArg<ValueEvent,Long> eventEventTranslator =  new EventTranslatorOneArg<ValueEvent, Long>() {
            @Override
            public void translateTo(ValueEvent valueEvent, long l, Long value) {
                valueEvent.setValue(value);
            }
        };
        disruptor.start();
        RingBuffer<ValueEvent> ringBuffer = disruptor.getRingBuffer();
        for(long i = 0;i<100;i++){
            ringBuffer.publishEvent(eventEventTranslator,i);
        }
        disruptor.shutdown();
        executorService.shutdown();
    }

    public static void useByBatchEventProcessor(){
       RingBuffer<ValueEvent> ringBuffer =  RingBuffer.createSingleProducer(new EventFactory<ValueEvent>() {
            @Override
            public ValueEvent newInstance() {
                return new ValueEvent();
            }
        },1024*1024,new YieldingWaitStrategy());
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();
        BatchEventProcessor<ValueEvent> c1 = new BatchEventProcessor(ringBuffer,sequenceBarrier,new FirstValueEventHandler());
        BatchEventProcessor<ValueEvent> c2 = new BatchEventProcessor(ringBuffer,sequenceBarrier,new SecondValueEventHandler());
        SequenceBarrier barrier3 = ringBuffer.newBarrier(c1.getSequence(),c2.getSequence());
        BatchEventProcessor<ValueEvent> c3 = new BatchEventProcessor(ringBuffer,barrier3,new ThirdValueEventHandler());
        ringBuffer.addGatingSequences(c3.getSequence());
        EventTranslatorOneArg<ValueEvent,Long> eventEventTranslator =  new EventTranslatorOneArg<ValueEvent, Long>() {
             @Override
            public void translateTo(ValueEvent valueEvent, long l, Long value) {
                valueEvent.setValue(value);
            }
        };

        for(long i = 0;i<100;i++){
            ringBuffer.publishEvent(eventEventTranslator,i);
        }
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.execute(c1);
        executorService.execute(c2);
        executorService.execute(c3);
        executorService.shutdown();
    }
}
