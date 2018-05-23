package threadpool;

import java.util.concurrent.*;

/**
 * Created by duanzx on 2018/5/18.
 */
public class ThreadPoolTestSecond {
/*
* 当前线程数量大于corePoolSize时候，会将任务放入无界队列里面
* */
    public static void main(String[] args)throws Exception{
        ExecutorService executor = new ThreadPoolExecutor(2,3,60, TimeUnit.SECONDS,new LinkedBlockingDeque<>());
        Task task1 = new Task("1","task-1");
        Task task2 = new Task("2","task-2");
        Task task3 = new Task("3","task-3");
        Task task4 = new Task("4","task-4");
        Task task5 = new Task("5","task-5");
        Task task6 = new Task("6","task-6");
        executor.execute(task1);
        executor.execute(task2);
        executor.execute(task3);
        executor.execute(task4);
        executor.execute(task5);
        executor.execute(task6);
        executor.shutdown();
    }
}
