package disruptor.batchEventProcessor;

import com.lmax.disruptor.EventHandler;

/**
 * Created by duanzx on 2018/6/12.
 */
public class ValueEventHandler implements EventHandler<ValueEvent> {

    @Override
    public void onEvent(ValueEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println(Thread.currentThread().getName() + " 正在处理数据：" + event.getValue()+",序列号："+sequence);
    }
}
