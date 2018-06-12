package disruptor.one2threeMultiCast;

import com.lmax.disruptor.EventHandler;

public class FirstValueEventHandler implements EventHandler<ValueEvent> {
    @Override
    public void onEvent(ValueEvent valueEvent, long l, boolean b) throws Exception {
        System.out.println(Thread.currentThread().getName() + " First handler 正在处理 Value ： " + valueEvent.getValue()+", 序列号：" + l);
    }
}
