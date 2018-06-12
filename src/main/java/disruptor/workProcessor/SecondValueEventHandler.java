package disruptor.workProcessor;

import com.lmax.disruptor.WorkHandler;

public class SecondValueEventHandler implements WorkHandler<ValueEvent> {
    @Override
    public void onEvent(ValueEvent valueEvent) throws Exception {
        System.out.println(Thread.currentThread().getName()+" Second 正在处理:"+valueEvent.getValue());
    }
}
