package masterWorker;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by duanzx on 2018/5/11.
 * 1.一个任务过来，交给master 处理
 * 2.
 */
public class Master {
    //ConcurretHashLinkedQueue
    //HashMap(String,Thread)
    // ConcurretHashMap
    private ConcurrentLinkedQueue<Task> taskQueueList = new ConcurrentLinkedQueue<Task>();

    private HashMap<String, Worker> workerHashMap = new HashMap<String, Worker>();

    private ConcurrentHashMap<String, Object> workerResultMap = new ConcurrentHashMap<String, Object>();

    public void submit(Task task){
        this.taskQueueList.add(task);
    }

    public Master(Worker worker, int workerCount) {
        for (int i = 0; i < workerCount; i++) {
            workerHashMap.put("worker-" + i, worker);
        }
    }

}
