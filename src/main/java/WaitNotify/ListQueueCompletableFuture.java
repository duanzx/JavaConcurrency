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

    public static void main(String[] args) throws Exception {
        ListQueueCompletableFuture listQueueCompletableFuture = new ListQueueCompletableFuture();
    }
}
