package threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by duanzx on 2018/5/25.
 */
public class ScheduledThreadPoolTest {

    public static void main(String[] args)throws Exception{
        ScheduledExecutorService scheduledService = Executors.newScheduledThreadPool(1);
        scheduledService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("-------------------");
            }
        },0,3, TimeUnit.SECONDS);
    }
}
