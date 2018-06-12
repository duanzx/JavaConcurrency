package disruptor.translator;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("正在处理事件：" + event.getValue() + "，序列号：" + sequence);
    }
}
