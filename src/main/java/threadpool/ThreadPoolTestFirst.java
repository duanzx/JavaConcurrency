package threadpool;

import masterWorker.*;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by duanzx on 2018/5/18.
 *
 * shutdownNow首先将线程池的状态设置成STOP，然后尝试停止所有的正在执行或暂停任务的线程，并返回等待执行任务的列表，
 * 而shutdown只是将线程池的状态设置成SHUTDOWN状态，然后中断所有没有正在执行任务的线程。
 * 当前线程数量小于coresize 时候，创建线程
 * 当前线程数据大于coresize时候，创建线程，知道达到maxSize
 * 当前线程数量大于maxSize，将 任务放到队列里
 * 如果有界队列满了，则执行饱和后的hanlder方法
 */
public class ThreadPoolTestFirst {
    public static void main(String[] args)throws Exception{
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,2,60, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(3), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("coreSize满，maxSize满，有界队列已满");
                r.run();
            }
        });
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
