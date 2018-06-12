package WaitNotify;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

/**
 * Created by duanzx on 2018/5/11.
 */
public class ListQueueCompletableFuture {
    private volatile ArrayList queueList = new ArrayList();

    public void addQueue(int ele) {
        System.out.println("向队列里添加元素：" + ele);
        queueList.add(ele);
    }

    public int getQueueSize() {
        return queueList.size();
    }

    //    Future是Java 5添加的类，用来描述一个异步计算的结果。你可以使用isDone方法检查计算是否完成，或者使用get阻塞住调用线程，直到计算完成返回结果，你也可以使用cancel方法停止任务的执行
    public static void main(String[] args) throws Exception {
        ListQueueCompletableFuture listQueueCompletableFuture = new ListQueueCompletableFuture();
    }
}
