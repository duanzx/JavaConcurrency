package disruptor.eventGroup;

import com.lmax.disruptor.EventHandler;

public class FirstValueEventHandler implements EventHandler<ValueEvent> {
    @Override
    public void onEvent(ValueEvent valueEvent, long l, boolean b) throws Exception {
        valueEvent.setName("First");
        System.out.println("First handler 正在处理 Value ： " + valueEvent.getValue());
    }
}
