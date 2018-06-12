package disruptor.workProcessor;

import com.lmax.disruptor.WorkHandler;

public class FirstValueEventHandler implements WorkHandler<ValueEvent> {
    @Override
    public void onEvent(ValueEvent valueEvent) throws Exception {
        System.out.println(Thread.currentThread().getName()+" First 正在处理:"+valueEvent.getValue());
    }
}
