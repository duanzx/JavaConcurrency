package disruptor.handler;

import com.lmax.disruptor.EventHandler;
import disruptor.bean.LongEvent;

public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("------------------------");
        System.out.println("LongEvent Value:" + event.getValue());
        System.out.println("Sequence:" + sequence);
        System.out.println("------------------------");
    }
}
