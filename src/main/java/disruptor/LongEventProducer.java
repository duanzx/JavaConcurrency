package disruptor;

import com.lmax.disruptor.RingBuffer;
import disruptor.bean.LongEvent;

import java.nio.ByteBuffer;

public class LongEventProducer {
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer byteBuffer) {
        long sequence = ringBuffer.next();
        try {
            LongEvent event = ringBuffer.get(sequence);
            event.setValue(byteBuffer.getLong(0));
        } catch (Exception e) {
            System.out.println("生产者填充数据出错，Sequence:" + sequence);
            e.printStackTrace();
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
