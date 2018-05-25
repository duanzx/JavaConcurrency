newCachedThreadPool:创建一个可以根据实际情况调整线程个数的线程池，不限制最大线程数量，如果有空闲的线程则执行任务，如果没有任务则不创建线程
并且每一个空闲线程会在60秒后自动回收。
public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, 2147483647, 60L, TimeUnit.SECONDS, new SynchronousQueue());
    }
newScheduledThreadPool ,返回一个ScheduledExecutorService对象，但该线程池可以指定线程的数量
public ScheduledThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
              new DelayedWorkQueue());
    }
newFixedThreadPool,返回一个固定数量的线程池，该方法的线程数始终不变，当有一个任务提交时，若线程池中空闲，则立即执行。若没有，则会被暂缓在一个任务队列中等待有空闲的线程执行。
public static ExecutorService newFixedThreadPool(int var0) {
        return new ThreadPoolExecutor(var0, var0, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
    }
 newSingleThreadPool,创建一个线程的线程池，若空闲则执行，若没有空闲线程，则暂缓在任务队列中。

 public static ExecutorService newSingleThreadExecutor() {
         return new Executors.FinalizableDelegatedExecutorService(new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue()));
     }


public ThreadPoolExecutor(int corePoolSize,//核心线程数量
                              int maximumPoolSize,//最大允许创建的线程数量
                              long keepAliveTime,//空闲线程的存活时间
                              TimeUnit unit,//存活时间单位
                              BlockingQueue<Runnable> workQueue,//当线程数量达到最大，不能继续创建时候，将任务放到该缓存队列里面，有空闲线程的话，再从该队列里取出。
                              ThreadFactory threadFactory) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
             threadFactory, defaultHandler);
    }

     public ThreadPoolExecutor(int corePoolSize,
                                  int maximumPoolSize,
                                  long keepAliveTime,
                                  TimeUnit unit,
                                  BlockingQueue<Runnable> workQueue,
                                  RejectedExecutionHandler handler) { //当线程达到最大数量，缓存队列已满，时候的饱和策略
     AbortPolicy :直接抛出运行时异常，系统正常工作
     DiscardPolicy：丢弃无法处理的任务，不给予任何处理
     DiscardOldestPolicy：丢弃最老的一个请求，尝试再次提交当前任务。
     CallerRunsPolicy：只要线程池未关闭，该策略直接在调用者线程中，运行当前被丢弃的任务。

     有界队列： 每次按照maxSize批量执行
     无界队列：每次按照coreSize批量执行。

