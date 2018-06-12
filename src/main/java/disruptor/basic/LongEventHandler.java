package disruptor.basic;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("------------------------");
        System.out.println("LongEvent Value:" + event.getValue());
        System.out.println("Sequence:" + sequence);
        System.out.println("------------------------");
    }
}
