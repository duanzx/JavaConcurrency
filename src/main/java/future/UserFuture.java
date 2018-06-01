package future;

import java.util.concurrent.*;

public class UserFuture implements Callable {
    @Override
    public Object call() throws Exception {
        Thread.sleep(3000);
        return "called result";
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        FutureTask<UserFuture> futureTask = new FutureTask<UserFuture>(new UserFuture());
        Future future = executorService.submit(futureTask);
        System.out.println("result:" + futureTask.get());
        System.out.println("----------------------");
        executorService.shutdown();
    }
}
