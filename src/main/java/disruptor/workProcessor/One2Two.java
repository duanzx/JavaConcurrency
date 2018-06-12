package disruptor.workProcessor;

import com.lmax.disruptor.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class One2Two {
/*
* BatchEventProcessor 与 WorkPool之间的区别？？？
*
* */
    public static void main(String[] args)throws Exception{
       RingBuffer<ValueEvent> ringBuffer =  RingBuffer.createSingleProducer(new EventFactory<ValueEvent>() {
            @Override
            public ValueEvent newInstance() {
                return new ValueEvent();
            }
        },1024*1024,new YieldingWaitStrategy());
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();
        WorkerPool<ValueEvent> workerPool = new WorkerPool(ringBuffer,sequenceBarrier,new FatalExceptionHandler(),new FirstValueEventHandler(),new SecondValueEventHandler());
        ringBuffer.addGatingSequences(workerPool.getWorkerSequences());
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        workerPool.start(executorService);
        for(long i = 0;i<1024*1024;i++){
            long sequence = ringBuffer.next();
            ringBuffer.get(sequence).setValue(i);
            ringBuffer.publish(sequence);
        }
//        workerPool.halt();
        executorService.shutdown();
    }
    }
