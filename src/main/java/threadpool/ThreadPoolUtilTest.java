package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolUtilTest {

    public static void main(String[] args) throws Exception {
        Executors.newSingleThreadExecutor();
        Executors.newCachedThreadPool();
        Executors.newScheduledThreadPool(5);
        Executors.newFixedThreadPool(10);
    }
}
