package disruptor.one2three;

import com.lmax.disruptor.EventHandler;

public class ThirdValueEventHandler implements EventHandler<ValueEvent> {
    @Override
    public void onEvent(ValueEvent valueEvent, long l, boolean b) throws Exception {
        System.out.println("Third handler 正在处理 Value ： " + valueEvent.getValue());
    }
}
